package digital.vault.dao.impl;

import digital.vault.dao.GenericDao;
import digital.vault.model.User;

import java.util.*;

public class UserDaoInMemory implements GenericDao<User>
{

    private Map<Integer,User> users=new HashMap<>(); //dicitona in care retinem username:user


    @Override
    public void create(User u)
    {
        users.put(u.getId(),u);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }


    @Override
    public User findById(int id)
    {
        return users.get(id);
    }
    @Override
    public void update(int id, User updatedUser)
    {
        if(users.containsKey(id))
        {
            users.put(id,updatedUser); //se suprascrie valoare veche
        }

    }
    @Override
    public void deleteById(int id)
    {
        users.remove(id);
    }

    public User findByUsername(String searchedUsername)
    {
        for(User u: users.values())
        {
            if(Objects.equals(u.getUsername(), searchedUsername))
                return u;
        }
        return null;//daca nu exista
    }
}
