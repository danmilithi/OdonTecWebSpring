package br.com.odontec.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

public class Agenda {
    private Integer id;

    @NotBlank(message = "Informe o paciente.")
    private String paciente;

    @NotBlank(message = "Informe o dentista.")
    private String dentista;

    @NotNull(message = "Informe a data.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    @NotNull(message = "Informe o horario.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime hora;

    @NotBlank(message = "Informe o tratamento.")
    private String tratamento;

    public Agenda() {
    }

    public Agenda(Integer id, String paciente, String dentista, LocalDate data, LocalTime hora, String tratamento) {
        this.id = id;
        this.paciente = paciente;
        this.dentista = dentista;
        this.data = data;
        this.hora = hora;
        this.tratamento = tratamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getDentista() {
        return dentista;
    }

    public void setDentista(String dentista) {
        this.dentista = dentista;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }
}
