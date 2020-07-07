package customer.service;

import customer.model.Customer;

import java.util.List;

public interface IService<T>{
    List<T> findAll();

    void save(T model);

    T findById(int id);

    void update(int id, T t);

    void remove(int id);
}
