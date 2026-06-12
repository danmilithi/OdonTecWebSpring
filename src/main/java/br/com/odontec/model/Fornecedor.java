package br.com.odontec.model;

import jakarta.validation.constraints.NotBlank;

public class Fornecedor {
    private Integer id;

    @NotBlank(message = "Informe o nome do fornecedor.")
    private String nome;

    private String telefone;
    private String produto;

    public Fornecedor() {
    }

    public Fornecedor(Integer id, String nome, String telefone, String produto) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.produto = produto;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
