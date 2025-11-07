/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.entities.Categoria;
import io.github.tutorial.jpa.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public class CategoriaRepositoryImpl extends CrudRepositoryImpl<Categoria, Long> implements CategoriaRepository {

    public CategoriaRepositoryImpl() {
        super(Categoria.class);
    }

    @Override
    public Optional<Categoria> findByNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("nome nulo ou vazio");
        }
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            String jpql = "select c from Categoria c where c.nome =:nome";
            TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
            query.setParameter("nome", nome);
            query.setMaxResults(1);
            return query.getResultStream().findFirst();
            
            
        }
    }
    
}
