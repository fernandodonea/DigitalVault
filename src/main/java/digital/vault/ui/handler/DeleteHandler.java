package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.service.VaultService;

public class DeleteHandler implements CommandHandler
{
    private final VaultService vaultService;
    private final SessionHolder session;

    public DeleteHandler(VaultService vaultService, SessionHolder session) {
        this.vaultService = vaultService;
        this.session = session;
    }

    @Override
    public void handle(String[] args) {
        if(args.length!=2)
        {
            System.out.println("Usage: rm <item-id>");
            return;
        }

        if(!session.isLoggedIn())
        {
            System.out.println("You must be logged in to delete an item:");
            return;
        }

        try{
            vaultService.deleteItem(args[1],session.getToken());
            System.out.println("Item deleted succesfully");
        }catch (ServiceException e)
        {
            System.out.println("[Cannot delete item]  "+e.getMessage());
        }
    }
}
