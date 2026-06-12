package br.com.odontec.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Tratamento {
    private Integer id;

    @NotBlank(message = "Informe o tratamento.")
    private String nome;

    @NotNull(message = "Informe o preco.")
    @DecimalMin(value = "0.01", message = "O preco deve ser maior que zero.")
    private BigDecimal preco;

    @NotNull(message = "Informe a duracao.")
    @DecimalMin(value = "0.1", message = "A duracao deve ser maior que zero.")
    private Double duracao;

    public Tratamento() {
    }

    public Tratamento(Integer id, String nome, BigDecimal preco, Double duracao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracao = duracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }
}
