package digital.vault.dao;

import digital.vault.model.VaultItem;

import java.util.ArrayList;
import java.util.List;

public class VauldDaoInMemory implements GenericDao<VaultItem>
{
    List<VaultItem> vaultItems =new ArrayList<>();

    @Override
    public void create(VaultItem item)
    {
        vaultItems.add(item);
    }
    @Override
    public List<VaultItem> findAll()
    {
        return vaultItems;
    }
    @Override
    public VaultItem findById(int id)
    {
        for(VaultItem i: vaultItems)
        {
            if(i.getId()==id)
                return i;
        }
        return  null;

    }
    @Override
    public void update(int id, VaultItem updatedObject)
    {
        for(int i = 0; i< vaultItems.size(); i++)
        {
            if(vaultItems.get(i).getId()==id)
            {
                vaultItems.set(i,updatedObject);
                break;
            }
        }

    }
    @Override
    public void deleteById(int id)
    {
        //lambda expresie
        vaultItems.removeIf(i->i.getId()==id);
    }
}
