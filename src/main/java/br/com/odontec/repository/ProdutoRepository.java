package br.com.odontec.repository;

import br.com.odontec.model.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository implements CrudRepository<Produto> {
    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Produto> listar(String busca) {
        String termo = "%" + (busca == null ? "" : busca.trim()) + "%";
        return jdbcTemplate.query(
                "SELECT id, nome, preco, fornecedor, quantidade FROM produto "
                + "WHERE nome LIKE ? OR fornecedor LIKE ? ORDER BY nome",
                this::mapear,
                termo,
                termo
        );
    }

    @Override
    public Optional<Produto> buscarPorId(Integer id) {
        return jdbcTemplate.query(
                "SELECT id, nome, preco, fornecedor, quantidade FROM produto WHERE id = ?",
                this::mapear,
                id
        ).stream().findFirst();
    }

    @Override
    public void salvar(Produto produto) {
        if (produto.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO produto (nome, preco, fornecedor, quantidade) VALUES (?, ?, ?, ?)",
                    produto.getNome().trim(),
                    produto.getPreco(),
                    produto.getFornecedor(),
                    produto.getQuantidade()
            );
            return;
        }

        jdbcTemplate.update(
                "UPDATE produto SET nome = ?, preco = ?, fornecedor = ?, quantidade = ? WHERE id = ?",
                produto.getNome().trim(),
                produto.getPreco(),
                produto.getFornecedor(),
                produto.getQuantidade(),
                produto.getId()
        );
    }

    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update("DELETE FROM produto WHERE id = ?", id);
    }

    @Override
    public long contar() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM produto", Long.class);
    }

    private Produto mapear(ResultSet rs, int rowNum) throws SQLException {
        return new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getBigDecimal("preco"),
                rs.getString("fornecedor"),
                rs.getInt("quantidade")
        );
    }
}
