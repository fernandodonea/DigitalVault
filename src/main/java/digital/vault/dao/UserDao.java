package digital.vault.dao;

import digital.vault.model.User;

public interface UserDao extends GenericDao<User>
{
    User findByUsername(String username);
}
