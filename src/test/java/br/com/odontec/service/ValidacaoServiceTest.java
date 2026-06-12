package br.com.odontec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ValidacaoServiceTest {

    @Test
    void deveAceitarTextoPreenchido() {
        ValidacaoService.textoObrigatorio("Paciente", "Nome");
    }

    @Test
    void deveRejeitarTextoVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ValidacaoService.textoObrigatorio("  ", "Nome")
        );
    }

    @Test
    void deveCalcularValorTotal() {
        BigDecimal total = ValidacaoService.calcularValorTotal(new BigDecimal("125.50"), 2);
        assertEquals(new BigDecimal("251.00"), total);
    }

    @Test
    void deveRejeitarQuantidadeInvalida() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ValidacaoService.calcularValorTotal(new BigDecimal("100.00"), 0)
        );
    }
}
