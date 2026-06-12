package br.com.odontec.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.odontec.model.Paciente;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import(PacienteRepository.class)
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository repository;

    @Test
    void deveCadastrarBuscarAtualizarEExcluirPaciente() {
        Paciente paciente = new Paciente(null, "Ana Lima", "11999990000", LocalDate.of(1995, 5, 12), "01001000");
        repository.salvar(paciente);

        Paciente salvo = repository.listar("Ana").get(0);
        assertEquals("Ana Lima", salvo.getNome());

        salvo.setTelefone("11888880000");
        repository.salvar(salvo);
        assertEquals("11888880000", repository.buscarPorId(salvo.getId()).orElseThrow().getTelefone());

        repository.excluir(salvo.getId());
        assertTrue(repository.buscarPorId(salvo.getId()).isEmpty());
    }
}
