package digital.vault.dao;

import digital.vault.model.vault.VaultItem;

import java.util.List;

public interface VaultDao extends GenericDao<VaultItem>
{
    List<VaultItem> findUserItems(String username);
}
