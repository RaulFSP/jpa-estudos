package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Produto;
import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    List<Produto> findByCategoriaNome(String nomeCategoria);

}
