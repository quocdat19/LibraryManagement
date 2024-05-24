package project.dao;

import java.util.List;

public interface IDao<T, K, S>{
    List<T> getAll();
    boolean create(T t);
    boolean update(T t);
    boolean delete(K k);
    T findById(K k);
    T findByName(S s);
    List<T> searchName(S s);
}
