package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.factory.VaultItemFactory;
import digital.vault.model.vault.VaultItem;
import digital.vault.service.VaultService;

public class AddItemHandler implements  CommandHandler
{
    private final VaultService vaultService;
    private final VaultItemFactory factory;
    private final SessionHolder session;

    public AddItemHandler(VaultService vaultService, VaultItemFactory factory, SessionHolder session) {
        this.vaultService = vaultService;
        this.factory = factory;
        this.session = session;
    }

    @Override
    public void handle(String[] args) {
        if(args.length!=2)
        {
            System.out.println("Usage: touch <note|card|web>");
            return;
        }

        if(!session.isLoggedIn())
        {
            System.out.println("You must be logged in to add an item");
            return;
        }


        //toata logica de creare o trimitem la factory
        VaultItem item= factory.create(args[1]);
        if(item==null)
        {
            return; //factory a afisat eroare
        }
        try{
            vaultService.addVaultItem(item, session.getToken());
            System.out.println("Item '"+item.getTitle()+ "' added succesfully");
        }
        catch (ServiceException e)
        {
            System.out.println("[Could not add item] "+e.getMessage());
        }

    }
}
