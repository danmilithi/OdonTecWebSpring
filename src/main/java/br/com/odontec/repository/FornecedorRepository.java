package br.com.odontec.repository;

import br.com.odontec.model.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FornecedorRepository implements CrudRepository<Fornecedor> {
    private final JdbcTemplate jdbcTemplate;

    public FornecedorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Fornecedor> listar(String busca) {
        String termo = "%" + (busca == null ? "" : busca.trim()) + "%";
        return jdbcTemplate.query(
                "SELECT id, nome, telefone, produto FROM fornecedor "
                + "WHERE nome LIKE ? OR produto LIKE ? ORDER BY nome",
                this::mapear,
                termo,
                termo
        );
    }

    @Override
    public Optional<Fornecedor> buscarPorId(Integer id) {
        return jdbcTemplate.query(
                "SELECT id, nome, telefone, produto FROM fornecedor WHERE id = ?",
                this::mapear,
                id
        ).stream().findFirst();
    }

    @Override
    public void salvar(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO fornecedor (nome, telefone, produto) VALUES (?, ?, ?)",
                    fornecedor.getNome().trim(),
                    fornecedor.getTelefone(),
                    fornecedor.getProduto()
            );
            return;
        }

        jdbcTemplate.update(
                "UPDATE fornecedor SET nome = ?, telefone = ?, produto = ? WHERE id = ?",
                fornecedor.getNome().trim(),
                fornecedor.getTelefone(),
                fornecedor.getProduto(),
                fornecedor.getId()
        );
    }

    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update("DELETE FROM fornecedor WHERE id = ?", id);
    }

    @Override
    public long contar() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM fornecedor", Long.class);
    }

    private Fornecedor mapear(ResultSet rs, int rowNum) throws SQLException {
        return new Fornecedor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("produto")
        );
    }
}
