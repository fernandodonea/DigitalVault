package digital.vault.dao;

import java.util.List;


///
///  DAO=Data acces object
///
public interface GenericDao <T>
{
    void create(T object);

    List<T> findAll();

    T findById(int id);

    void update(int id, T updatedObject);

    void deleteById(int id);
}
