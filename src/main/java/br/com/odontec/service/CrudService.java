package br.com.odontec.service;

import br.com.odontec.repository.CrudRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class CrudService<T> {
    private final CrudRepository<T> repository;

    protected CrudService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> listar(String busca) {
        return repository.listar(busca);
    }

    public T buscar(Integer id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro nao encontrado."));
    }

    public void salvar(T entidade) {
        repository.salvar(entidade);
    }

    public void excluir(Integer id) {
        repository.excluir(id);
    }

    public long contar() {
        return repository.contar();
    }
}
