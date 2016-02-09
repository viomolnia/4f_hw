package io.fourfinanceit.core.database;

/**
 * Created by Anna on 03.02.2016.
 */
public interface CRUDOperationDAO<E, K> {

    void create(E entity);

    E getById(K key);

    E getRequired(K key);

    void update(E entity);

    void delete(E entity);

}
