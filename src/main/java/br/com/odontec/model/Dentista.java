package br.com.odontec.model;

import jakarta.validation.constraints.NotBlank;

public class Dentista {
    private Integer id;

    @NotBlank(message = "Informe o nome do dentista.")
    private String nome;

    @NotBlank(message = "Informe a especialidade.")
    private String especialidade;

    public Dentista() {
    }

    public Dentista(Integer id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
