package org.gieback.DAO;

import java.util.List;

    public interface IDao<T > {
        List<T> getAll();

        void    ajouter(T t);

        T getById(int id);
}
