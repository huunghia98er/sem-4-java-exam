package org.sem4.exam.repository;

import java.util.List;

public interface Repository<T, K> {
    List<T> all();
    T findById(K id);
    boolean create(T s);
    boolean update(T s);
    boolean delete(T s);
    <K> T find(K id);
}
