package br.com.odontec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import(UsuarioRepository.class)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    void deveAutenticarUsuarioValido() {
        var usuario = repository.autenticar("Daniel", "123").orElseThrow();
        assertEquals("GERENTE", usuario.tipoUsuario());
    }

    @Test
    void deveRejeitarSenhaInvalida() {
        assertTrue(repository.autenticar("Daniel", "senha-errada").isEmpty());
    }
}
