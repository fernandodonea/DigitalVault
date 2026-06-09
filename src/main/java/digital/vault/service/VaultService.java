package digital.vault.service;

import digital.vault.dao.impl.VaultDaoInMemory;
import digital.vault.exception.ServiceException;
import digital.vault.model.vault.VaultItem;

import java.util.List;

public class VaultService
{
    private VaultDaoInMemory vaultDao;
    private AuthService authService;

    //injectam dependintele
    public VaultService(VaultDaoInMemory vaultDao, AuthService authService) {
        this.vaultDao = vaultDao;
        this.authService = authService;
    }

    /*
        FUNCTIILE SEIFULUI PRIMESC TOKEN-UL, NU USERNAME-UL
     */


    public void addItem(VaultItem item, String token)
    {
        String username= authService.validateTokenAndGetUsername(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }

        item.setUsernameOwner(username);
        vaultDao.create(item);
    }

    public void displayVault(String token)
    {
        String username= authService.validateTokenAndGetUsername(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }

        List<VaultItem> usersVaultItems=vaultDao.findUserItems(username);
        if(usersVaultItems==null){
            throw new ServiceException("Vault empty");
        }

        usersVaultItems.forEach(System.out::println);

    }

    public void deleteItem(int id, String token)
    {
        String username= authService.validateTokenAndGetUsername(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }
        else{
            vaultDao.deleteById(id);
        }
    }


}
