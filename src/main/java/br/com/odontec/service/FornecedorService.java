package br.com.odontec.service;

import br.com.odontec.model.Fornecedor;
import br.com.odontec.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService extends CrudService<Fornecedor> {
    public FornecedorService(FornecedorRepository repository) {
        super(repository);
    }
}
