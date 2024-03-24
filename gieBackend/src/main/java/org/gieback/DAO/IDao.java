package org.gieback.DAO;

import java.util.List;

    public interface IDao<T > {
        List<T> getAll();

        void add(T t);

        T getById(int id);

        T delete(T t);
    }