package br.com.odontec.service;

import br.com.odontec.model.Tratamento;
import br.com.odontec.repository.TratamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class TratamentoService extends CrudService<Tratamento> {
    public TratamentoService(TratamentoRepository repository) {
        super(repository);
    }
}
