package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Produto;
import io.github.tutorial.jpa.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProdutoRepositoryImpl extends CrudRepositoryImpl<Produto, Long> implements ProdutoRepository {

    public ProdutoRepositoryImpl() {
        super(Produto.class);
    }

    @Override
    public List<Produto> findByCategoriaNome(String nomeCategoria) {
        try(var em = JPAUtil.getInstance().getEmf().createEntityManager()){
            String jpql = "select p from Produto p join fetch p.categoria c where c.nome =:nomeCategoria";
            TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
            query.setParameter("nomeCategoria", nomeCategoria);
            return query.getResultList();
        }
    }

}
