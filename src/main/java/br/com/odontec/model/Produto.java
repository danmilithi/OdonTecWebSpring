package br.com.odontec.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Produto {
    private Integer id;

    @NotBlank(message = "Informe o nome do produto.")
    private String nome;

    @NotNull(message = "Informe o preco.")
    @DecimalMin(value = "0.01", message = "O preco deve ser maior que zero.")
    private BigDecimal preco;

    private String fornecedor;

    @NotNull(message = "Informe a quantidade.")
    @Min(value = 0, message = "A quantidade nao pode ser negativa.")
    private Integer quantidade;

    public Produto() {
    }

    public Produto(Integer id, String nome, BigDecimal preco, String fornecedor, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
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

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
