package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.model.Category;
import digital.vault.model.vault.Card;
import digital.vault.model.vault.SecureNote;
import digital.vault.model.vault.VaultItem;
import digital.vault.model.vault.WebCredential;
import digital.vault.service.VaultService;
import digital.vault.ui.InputReader;

public class UpdateHandler implements CommandHandler
{
    private final VaultService vaultService;
    private final SessionHolder session;
    private final InputReader reader;

    public UpdateHandler(VaultService vaultService, SessionHolder session, InputReader reader) {
        this.vaultService = vaultService;
        this.session = session;
        this.reader = reader;
    }
    @Override
    public void handle(String[] args)
    {
        if(args.length!=2)
        {
            System.out.println("Usage: nano <item-id>");
            return;
        }
        if(!session.isLoggedIn())
        {
            System.out.println("Must be logged in to update an item");
            return;
        }
        try{
            VaultItem item= vaultService.getVaultItemById(args[1],session.getToken());
            VaultItem updated=editItem(item);
            if(updated!=null)
            {
                vaultService.updateVaultItem(args[1],updated, session.getToken());
                System.out.println("Item updated");
            }
        }
        catch (ServiceException e)
        {
            System.out.println("[Cannot edit item]: " + e.getMessage());
        }
    }

    private VaultItem editItem(VaultItem item)
    {
        System.out.println("...Editing item with the following id:"+item.getId());

        System.out.println("Current title: "+item.getTitle());
        String newTitle=reader.readLine("New title");
        if(newTitle.isBlank()) newTitle=item.getTitle();

        System.out.println("Current category: "+item.getCategory());
        String categoryStr=reader.readLine("New category: ");
        Category newCategory;
        if(categoryStr.isBlank()){
            newCategory=item.getCategory();
        }else{
            try{
                newCategory=Category.valueOf(categoryStr.toUpperCase());
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Invalid category. Keepin curernt");
                newCategory=item.getCategory();
            }
        }


        if(item instanceof SecureNote note)
        {
            System.out.println("Current content: "+note.getContent());
            String newContent=reader.readLine("New content: ");
            if(newContent.isBlank())
                newContent=note.getContent();

            return new SecureNote(newTitle,newCategory, item.getUsernameOwner(), newContent);
        } else if (item instanceof WebCredential web)
        {
            System.out.println("Current url: "+web.getUrl());
            String newUrl= reader.readLine("New url:");
            if(newUrl.isBlank())
                newUrl=web.getUrl();

            System.out.println("Current username: "+web.getUsername());
            String newUsername= reader.readLine("New username:");
            if(newUsername.isBlank())
                newUsername=web.getUsername();

            System.out.print("Current password: "+maskPassword(web.getPassword()));
            String newPassword= reader.readLine("New Password:");
            if(newPassword.isBlank())
                newPassword=web.getPassword();

            return new WebCredential(newTitle, newCategory, item.getUsernameOwner(), newUrl, newUsername, newPassword);
        } else if (item instanceof Card card)
        {
            System.out.println("Current card number: "+maskCardNumber(card.getCardNumber()));
            String newCardNumber=reader.readLine("New card number: ");
            if(newCardNumber.isBlank())
                newCardNumber=card.getCardNumber();

            System.out.println("Current card holder name: "+card.getCardHolderName());
            String newCardHolderName= reader.readLine("New card holder name:");
            if(newCardHolderName.isBlank())
                newCardHolderName=card.getCardHolderName();

            System.out.println("Current cvv:  ***");
            String newCvv= reader.readLine("New cvv:");
            if(newCvv.isBlank())
                newCvv=String.valueOf(card.getCvv());

            return new Card(newTitle, newCategory, card.getUsernameOwner(), newCardNumber, newCardHolderName, Integer.parseInt(newCvv));
        }
        return null;


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
