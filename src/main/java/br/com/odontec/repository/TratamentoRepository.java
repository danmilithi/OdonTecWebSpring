package br.com.odontec.repository;

import br.com.odontec.model.Tratamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TratamentoRepository implements CrudRepository<Tratamento> {
    private final JdbcTemplate jdbcTemplate;

    public TratamentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tratamento> listar(String busca) {
        String termo = "%" + (busca == null ? "" : busca.trim()) + "%";
        return jdbcTemplate.query(
                "SELECT id, nome, preco, duracao FROM tratamento WHERE nome LIKE ? ORDER BY nome",
                this::mapear,
                termo
        );
    }

    @Override
    public Optional<Tratamento> buscarPorId(Integer id) {
        return jdbcTemplate.query(
                "SELECT id, nome, preco, duracao FROM tratamento WHERE id = ?",
                this::mapear,
                id
        ).stream().findFirst();
    }

    @Override
    public void salvar(Tratamento tratamento) {
        if (tratamento.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO tratamento (nome, preco, duracao) VALUES (?, ?, ?)",
                    tratamento.getNome().trim(),
                    tratamento.getPreco(),
                    tratamento.getDuracao()
            );
            return;
        }

        jdbcTemplate.update(
                "UPDATE tratamento SET nome = ?, preco = ?, duracao = ? WHERE id = ?",
                tratamento.getNome().trim(),
                tratamento.getPreco(),
                tratamento.getDuracao(),
                tratamento.getId()
        );
    }

    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update("DELETE FROM tratamento WHERE id = ?", id);
    }

    @Override
    public long contar() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tratamento", Long.class);
    }

    private Tratamento mapear(ResultSet rs, int rowNum) throws SQLException {
        return new Tratamento(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getBigDecimal("preco"),
                rs.getDouble("duracao")
        );
    }
}
