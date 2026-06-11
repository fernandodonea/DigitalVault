package digital.vault.service;

import digital.vault.dao.VaultDao;
import digital.vault.exception.ServiceException;
import digital.vault.exception.ValidationException;
import digital.vault.model.vault.Card;
import digital.vault.model.vault.VaultItem;
import digital.vault.model.vault.WebCredential;
import digital.vault.validation.CardNumberValidator;
import digital.vault.validation.CvvValidator;
import digital.vault.validation.UrlValidator;

import java.util.List;

public class VaultService
{
    private VaultDao vaultDao;
    private AuthService authService;

    //injectam dependintele
    public VaultService(VaultDao vaultDao, AuthService authService) {
        this.vaultDao = vaultDao;
        this.authService = authService;
    }

    /*
        FUNCTIILE SEIFULUI PRIMESC TOKEN-UL, NU USERNAME-UL
     */


    public void addVaultItem(VaultItem item, String token)
    {
        String username= authService.getUsernameFromToken(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }

        try{
            if(item instanceof Card card)
            {
                new CardNumberValidator().validate(card.getCardNumber());
                new CvvValidator().validate(card.getCvv());
            } else if (item instanceof WebCredential web) {
                new UrlValidator().validate(web.getUrl());
            }
        }catch (ValidationException v){
            throw new ServiceException("Item validation failed: "+v.getMessage());
        }
        item.setUsernameOwner(username);
        vaultDao.create(item);
        AuditService.getInstance().log("add-item");
    }

    public List<VaultItem> getUsersVaultItems(String token)
    {
        String username=authService.getUsernameFromToken(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }

        List<VaultItem> userItems=vaultDao.findUserItems(username);
        if(userItems==null || userItems.isEmpty()){
            throw new ServiceException("Vault empty");
        }

        AuditService.getInstance().log("get-vault-items");
        return userItems;
    }

    public void deleteVaultItem(String id, String token)
    {
        String username= authService.getUsernameFromToken(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }
        VaultItem item=vaultDao.findById(id);
        if(item==null){
            throw new ServiceException("Item not found");
        }
        if(!item.getUsernameOwner().equals(username)){
            throw new ServiceException("That is not your item");
        }

        vaultDao.deleteById(id);
        AuditService.getInstance().log("delete-item");
    }


    public VaultItem getVaultItemById(String id, String token)
    {
        String username= authService.getUsernameFromToken(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }
        VaultItem item=vaultDao.findById(id);
        if(item==null || !item.getUsernameOwner().equals(username)){
            throw new ServiceException("No item found");
        }

        AuditService.getInstance().log("get-vault-item");
        return item;
    }

    public void updateVaultItem(String id, VaultItem updatedItem, String token)
    {
        String username= authService.getUsernameFromToken(token);
        if(username==null){
            throw new ServiceException("Invalid session. Log in again");
        }
        VaultItem existingItem =vaultDao.findById(id);
        if(existingItem==null || !existingItem.getUsernameOwner().equals(username)){
            throw new ServiceException("No item found");
        }

        try {
            if (updatedItem instanceof Card card) {
                new CardNumberValidator().validate(card.getCardNumber());
                new CvvValidator().validate(card.getCvv());
            } else if (updatedItem instanceof WebCredential web) {
                new UrlValidator().validate(web.getUrl());
            }
        }
        catch (ValidationException v)
        {
            throw new ServiceException("[Item validation failed: "+v.getMessage());
        }
        updatedItem.setUsernameOwner(username);
        vaultDao.update(id, updatedItem);
        AuditService.getInstance().log("update-item");
    }


}
