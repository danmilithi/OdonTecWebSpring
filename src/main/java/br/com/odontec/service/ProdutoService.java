package br.com.odontec.service;

import br.com.odontec.model.Produto;
import br.com.odontec.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends CrudService<Produto> {
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
    }
}
