package br.com.odontec.model;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Paciente {
    private Integer id;

    @NotBlank(message = "Informe o nome do paciente.")
    private String nome;

    private String telefone;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    private String cep;

    public Paciente() {
    }

    public Paciente(Integer id, String nome, String telefone, LocalDate dataNascimento, String cep) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
