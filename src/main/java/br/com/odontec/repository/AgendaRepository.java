package br.com.odontec.repository;

import br.com.odontec.model.Agenda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgendaRepository implements CrudRepository<Agenda> {
    private final JdbcTemplate jdbcTemplate;

    public AgendaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Agenda> listar(String busca) {
        String termo = "%" + (busca == null ? "" : busca.trim()) + "%";
        return jdbcTemplate.query(
                "SELECT id, paciente, dentista, data, hora, tratamento FROM agenda "
                + "WHERE paciente LIKE ? OR dentista LIKE ? OR tratamento LIKE ? ORDER BY data, hora",
                this::mapear,
                termo,
                termo,
                termo
        );
    }

    @Override
    public Optional<Agenda> buscarPorId(Integer id) {
        return jdbcTemplate.query(
                "SELECT id, paciente, dentista, data, hora, tratamento FROM agenda WHERE id = ?",
                this::mapear,
                id
        ).stream().findFirst();
    }

    @Override
    public void salvar(Agenda agenda) {
        if (agenda.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO agenda (paciente, dentista, data, hora, tratamento) VALUES (?, ?, ?, ?, ?)",
                    agenda.getPaciente().trim(),
                    agenda.getDentista().trim(),
                    agenda.getData(),
                    agenda.getHora(),
                    agenda.getTratamento().trim()
            );
            return;
        }

        jdbcTemplate.update(
                "UPDATE agenda SET paciente = ?, dentista = ?, data = ?, hora = ?, tratamento = ? WHERE id = ?",
                agenda.getPaciente().trim(),
                agenda.getDentista().trim(),
                agenda.getData(),
                agenda.getHora(),
                agenda.getTratamento().trim(),
                agenda.getId()
        );
    }

    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update("DELETE FROM agenda WHERE id = ?", id);
    }

    @Override
    public long contar() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM agenda", Long.class);
    }

    private Agenda mapear(ResultSet rs, int rowNum) throws SQLException {
        return new Agenda(
                rs.getInt("id"),
                rs.getString("paciente"),
                rs.getString("dentista"),
                rs.getDate("data").toLocalDate(),
                rs.getTime("hora").toLocalTime(),
                rs.getString("tratamento")
        );
    }
}
