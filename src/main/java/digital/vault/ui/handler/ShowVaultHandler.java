package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.model.vault.VaultItem;
import digital.vault.service.AuthService;
import digital.vault.service.VaultService;

import java.util.List;

public class ShowVaultHandler implements  CommandHandler
{
    private final VaultService vaultService;
    private final SessionHolder session;


    public ShowVaultHandler(VaultService vaultService, SessionHolder session) {
        this.vaultService = vaultService;
        this.session = session;
    }
    @Override
    public void handle(String[] args) {
        if(args.length!=1)
        {
            System.out.println("Usage: ls");
            return;
        }

        if(!session.isLoggedIn())
        {
            System.out.println("You must be logged in to see your vault");
            return;
        }

        try{
            List<VaultItem> vaultItemList=vaultService.getVaultItems(session.getToken());
            vaultItemList.forEach(System.out::println);
        }
        catch (ServiceException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
