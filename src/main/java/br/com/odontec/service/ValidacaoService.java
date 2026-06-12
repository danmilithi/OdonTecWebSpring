package br.com.odontec.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ValidacaoService {
    private ValidacaoService() {
    }

    public static void textoObrigatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " e obrigatorio.");
        }
    }

    public static void numeroPositivo(double valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " deve ser maior que zero.");
        }
    }

    public static BigDecimal calcularValorTotal(BigDecimal valorUnitario, int quantidade) {
        if (valorUnitario == null || valorUnitario.signum() <= 0) {
            throw new IllegalArgumentException("O valor unitario deve ser maior que zero.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade)).setScale(2, RoundingMode.HALF_UP);
    }
}
