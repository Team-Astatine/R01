package teamzesa.DataBase;

import teamzesa.DataBase.entity.User;

public interface DAO<K,E> {

    Boolean insert(E e);

    E select(K k);

    Boolean update(E e);

    Boolean clear();
}
