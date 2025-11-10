package io.github.tutorial.jpa.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<X, Y> {

    X save(X entity);
    
    List<X> saveAll(Collection<X> entities);

    Optional<X> findById(Y id);

    void delete(X entity);

    List<X> findAll();
    
    void merge(X entity);
}
