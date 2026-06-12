package br.com.odontec.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> listar(String busca);

    Optional<T> buscarPorId(Integer id);

    void salvar(T entidade);

    void excluir(Integer id);

    long contar();
}
