package teamzesa.Data.Interface.User;

public interface AccessObject<K, E> {

    Boolean insert(E e);

    E select(K k);

    Boolean update(E e);

    Boolean clear();
}
