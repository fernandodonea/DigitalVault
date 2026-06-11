package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.model.vault.Card;
import digital.vault.model.vault.SecureNote;
import digital.vault.model.vault.VaultItem;
import digital.vault.model.vault.WebCredential;
import digital.vault.service.VaultService;

public class ShowHandler implements  CommandHandler
{
    private final VaultService vaultService;
    private final SessionHolder session;

    public ShowHandler(VaultService vaultService, SessionHolder session) {
        this.vaultService = vaultService;
        this.session = session;
    }

    @Override
    public void handle(String[] args) {
        if (args.length!=2) {
            System.out.println("cat <item-id>");
            return;
        }
        if(!session.isLoggedIn())
        {
            System.out.println("You must be logged in to view an item");
            return;
        }
        try{
            VaultItem item= vaultService.getVaultItemById(args[1],session.getToken());
            printItem(item);
        }catch (ServiceException e)
        {
            System.out.println("[Cannot read item]: "+e.getMessage());
        }
    }

    private void printItem(VaultItem item)
    {
        if (item instanceof WebCredential web)
        {
        System.out.println("  Type     : Web Credential");
        System.out.println("  URL      : " + web.getUrl());
        System.out.println("  Username : " + web.getUsername());
        System.out.println("  Password : " + maskPassword(web.getPassword()));
        }
        else if (item instanceof Card card) {
            System.out.println("  Type     : Payment Card");
            System.out.println("  Number   : " + maskCardNumber(card.getCardNumber()));
            System.out.println("  Holder   : " + card.getCardHolderName());
            System.out.println("  CVV      : ***");
        }
        else if (item instanceof SecureNote note)
        {
            System.out.println("  Type     : Secure Note");
            System.out.println("  Content  : " + note.getContent());
        }
        System.out.println("--------------------------------------");
    }


    private String maskPassword(String password) {
        if (password == null || password.length() <= 2) return "***";
        return password.charAt(0) + "*".repeat(password.length() - 2) + password.charAt(password.length() - 1);
    }

    private String maskCardNumber(String number) {
        String digits = number.replaceAll(" ", "");
        if (digits.length() < 4) return "****";
        return "**** **** **** " + digits.substring(digits.length() - 4);
    }


}
