package digital.vault.service;

import digital.vault.dao.VaultDaoInMemory;
import digital.vault.model.VaultItem;

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
        if(username!=null)
        {
            item.setUsernameOwner(username);
            vaultDao.create(item);
            System.out.println("Vault item salvat cu succes");
        }
        else{
            System.out.println("Nu esti autorizat. Logheaza-te");
        }
    }

    public void displayVault(String token)
    {
        String username= authService.validateTokenAndGetUsername(token);
        if(username==null)
        {
            System.out.println("Sesiune expirata sau invalida");
            return;
        }

        List<VaultItem> usersVaultItems=vaultDao.findUsersItems(username);
        if(usersVaultItems==null)
        {
            System.out.println("Seif gol");
        }
        else{
            usersVaultItems.forEach(VaultItem::displayItem);
        }
    }

    public void deleteItem(int id, String token)
    {
        String username= authService.validateTokenAndGetUsername(token);
        if(username==null)
        {
            System.out.println("Sesiune expirata sau invalida");
            return;
        }
        else{
            vaultDao.deleteById(id);
        }
    }


}
