package org.gieback.Service;

import java.util.List;

public interface IService<T> {

        void ajouter(T t);
        List<T> getAll();
       // T trouverId(int id);

    }

