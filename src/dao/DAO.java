package dao;

import java.util.List;

public interface DAO<T> {
    T get(int id);
    List<T> getAll();
    boolean save(T obj);
    boolean update(T obj);
    boolean delete(T obj);
}