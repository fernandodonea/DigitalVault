package digital.vault.dao;

import java.util.List;


///
///  DAO=Data acces object
///
public interface GenericDao <T>
{
    void create(T object);

    List<T> findAll();

    T findById(String id);

    void update(String id, T updatedObject);

    void deleteById(String id);
}
