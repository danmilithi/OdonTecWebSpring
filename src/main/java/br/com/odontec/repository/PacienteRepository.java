package br.com.odontec.repository;

import br.com.odontec.model.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PacienteRepository implements CrudRepository<Paciente> {
    private final JdbcTemplate jdbcTemplate;

    public PacienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Paciente> listar(String busca) {
        String termo = "%" + (busca == null ? "" : busca.trim()) + "%";
        return jdbcTemplate.query(
                "SELECT id, nome, telefone, data_nascimento, cep FROM paciente "
                + "WHERE nome LIKE ? OR telefone LIKE ? ORDER BY nome",
                this::mapear,
                termo,
                termo
        );
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return jdbcTemplate.query(
                "SELECT id, nome, telefone, data_nascimento, cep FROM paciente WHERE id = ?",
                this::mapear,
                id
        ).stream().findFirst();
    }

    @Override
    public void salvar(Paciente paciente) {
        if (paciente.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO paciente (nome, telefone, data_nascimento, cep) VALUES (?, ?, ?, ?)",
                    paciente.getNome().trim(),
                    paciente.getTelefone(),
                    paciente.getDataNascimento(),
                    paciente.getCep()
            );
            return;
        }

        jdbcTemplate.update(
                "UPDATE paciente SET nome = ?, telefone = ?, data_nascimento = ?, cep = ? WHERE id = ?",
                paciente.getNome().trim(),
                paciente.getTelefone(),
                paciente.getDataNascimento(),
                paciente.getCep(),
                paciente.getId()
        );
    }

    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update("DELETE FROM paciente WHERE id = ?", id);
    }

    @Override
    public long contar() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM paciente", Long.class);
    }

    private Paciente mapear(ResultSet rs, int rowNum) throws SQLException {
        return new Paciente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getDate("data_nascimento") == null ? null : rs.getDate("data_nascimento").toLocalDate(),
                rs.getString("cep")
        );
    }
}
