package com.example.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    // CREATE
    T save(T entity);

    // READ BY ID
    Optional<T> findById(ID id);

    // READ ALL
    List<T> findAll();

    // UPDATE
    void update(T entity);

    // DELETE
    void delete(T entity);

    // DELETE BY ID
    void deleteById(ID id);
}
