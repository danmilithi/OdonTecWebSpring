package br.com.odontec.service;

import br.com.odontec.model.Paciente;
import br.com.odontec.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class PacienteService extends CrudService<Paciente> {
    public PacienteService(PacienteRepository repository) {
        super(repository);
    }
}
