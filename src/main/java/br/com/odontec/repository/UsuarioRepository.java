package br.com.odontec.repository;

import br.com.odontec.model.Usuario;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Usuario> autenticar(String login, String senha) {
        return jdbcTemplate.query(
                "SELECT id, login, tipo_usuario FROM usuario WHERE login = ? AND senha = ?",
                (rs, rowNum) -> new Usuario(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("tipo_usuario")
                ),
                login,
                senha
        ).stream().findFirst();
    }
}
