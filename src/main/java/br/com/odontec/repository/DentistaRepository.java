package br.com.odontec.repository;

import br.com.odontec.model.Dentista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DentistaRepository implements CrudRepository<Dentista> {
    private final JdbcTemplate jdbcTemplate;

    public DentistaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Dentista> listar(String busca) {
        String termo = "%" + (busca == null ? "" : busca.trim()) + "%";
        return jdbcTemplate.query(
                "SELECT id, nome, especialidade FROM dentista "
                + "WHERE nome LIKE ? OR especialidade LIKE ? ORDER BY nome",
                this::mapear,
                termo,
                termo
        );
    }

    @Override
    public Optional<Dentista> buscarPorId(Integer id) {
        return jdbcTemplate.query(
                "SELECT id, nome, especialidade FROM dentista WHERE id = ?",
                this::mapear,
                id
        ).stream().findFirst();
    }

    @Override
    public void salvar(Dentista dentista) {
        if (dentista.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO dentista (nome, especialidade) VALUES (?, ?)",
                    dentista.getNome().trim(),
                    dentista.getEspecialidade().trim()
            );
            return;
        }

        jdbcTemplate.update(
                "UPDATE dentista SET nome = ?, especialidade = ? WHERE id = ?",
                dentista.getNome().trim(),
                dentista.getEspecialidade().trim(),
                dentista.getId()
        );
    }

    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update("DELETE FROM dentista WHERE id = ?", id);
    }

    @Override
    public long contar() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM dentista", Long.class);
    }

    private Dentista mapear(ResultSet rs, int rowNum) throws SQLException {
        return new Dentista(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("especialidade")
        );
    }
}
