package br.com.odontec.service;

import br.com.odontec.model.Agenda;
import br.com.odontec.repository.AgendaRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendaService extends CrudService<Agenda> {
    public AgendaService(AgendaRepository repository) {
        super(repository);
    }
}
