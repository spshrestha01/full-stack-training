package com.vastika.training.java.cms.repositories;

import java.util.List;

public interface CrudRepository<T> {

    void insert(T t);
    List<T> findAll();
    T findById(int id);
    boolean update(T t);
    boolean deleteById(int id);

}
