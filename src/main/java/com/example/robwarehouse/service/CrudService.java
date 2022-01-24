package com.example.robwarehouse.service;

import java.util.Collection;

public interface CrudService<T> {

    T newObject();

    Long create(T newObject);

    void update(T update);

    T get(Long id);

    void delete(Long id);

    Collection<T> getAll();
}
