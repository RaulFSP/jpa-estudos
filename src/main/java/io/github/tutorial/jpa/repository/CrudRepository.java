package io.github.tutorial.jpa.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<X, Y> {

    X save(X entity);

    Optional<X> findById(Y id);

    void delete(X entity);

    List<X> findAll();
}
