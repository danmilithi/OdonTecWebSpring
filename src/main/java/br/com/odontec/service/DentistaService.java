package br.com.odontec.service;

import br.com.odontec.model.Dentista;
import br.com.odontec.repository.DentistaRepository;
import org.springframework.stereotype.Service;

@Service
public class DentistaService extends CrudService<Dentista> {
    public DentistaService(DentistaRepository repository) {
        super(repository);
    }
}
