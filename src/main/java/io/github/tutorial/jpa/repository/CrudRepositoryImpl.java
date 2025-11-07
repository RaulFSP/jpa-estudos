package io.github.tutorial.jpa.repository;

import io.github.tutorial.jpa.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CrudRepositoryImpl<X, Y> implements CrudRepository<X, Y> {

    private final Class<X> entity;
    

    public CrudRepositoryImpl(Class<X> entity) {
        this.entity = entity;
        
    }

    @Override
    public X save(X entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entidade é nula");
        }
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Optional findById(Y id) {
        if (id == null) {
            throw new IllegalArgumentException("id nulo");
        }
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            return Optional.ofNullable(em.find(entity, id));
        }
    }

    @Override
    public void delete(X entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entidade é nula");
        }
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            em.remove(entity);

        }
    }

    @Override
    public List<X> findAll() {
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            String jpql = "select e from " + entity.getSimpleName() + " e";
            TypedQuery<X> query = em.createQuery(jpql, entity);
            return query.getResultList();
        }
    }

    @Override
    public List<X> saveAll(Collection<X> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("Entidade é nula");
        }
        List<X> savedEntities = new LinkedList<>();
        try (var em = JPAUtil.getInstance().getEmf().createEntityManager()) {
            em.getTransaction().begin();
            for(var entity : entities){
            em.persist(entity);
                savedEntities.add(entity);
            }
            em.getTransaction().commit();
            
            return savedEntities;
        }
    }

}
