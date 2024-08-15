package teamzesa.DataBase;

public interface DAO<K, E> {

    Boolean insert(E e);

    E select(K k);

    Boolean update(E e);

    Boolean clear();
}
