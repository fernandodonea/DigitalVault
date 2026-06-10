package digital.vault.ui;

import digital.vault.exception.ServiceException;
import digital.vault.model.Category;
import digital.vault.model.vault.Card;
import digital.vault.model.vault.SecureNote;
import digital.vault.model.vault.VaultItem;
import digital.vault.model.vault.WebCredential;
import digital.vault.service.AuthService;
import digital.vault.service.VaultService;

import java.util.Scanner;

public class TerminalService
{
    private AuthService authService;
    private VaultService vaultService;
    private String currenUserToken=null;

    public TerminalService(AuthService authService, VaultService vaultService) {

        this.authService=authService;
        this.vaultService=vaultService;
    }

    public void start()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("new Digital Vault Terminal");
        System.out.println("type 'help' for a list of commands");

        while(true)
        {
            if(currenUserToken==null)
            {
                System.out.print(">vault ");
            }
            else
            {
                String username=authService.validateTokenAndGetUsername(currenUserToken);
                System.out.print(username+"@vault> ");
            }


            String input=sc.nextLine().trim();


            String[] args=input.split("\\s");
            String command=args[0];

            switch (command)
            {
                case "register":
                    handleRegister(args);
                    break;
                case "login":
                    handleLogin(args);
                    break;
                case "logout":
                    handleLogout(args);
                    break;
                case "ls":
                    handeShowVault(args);
                    break;
                case "touch":
                    handleAddItem(args);
                    break;
                case "quit":
                    return;
            }


        }
    }

    private void handleRegister(String[] args)
    {
        if(args.length!=4)
        {
            System.out.println("Incorect number of paramters");
            return;
        }

        try{
            authService.register(args[1],args[2],args[3]);
            System.out.println("Account created succesfully");
        }
        catch (ServiceException e)
        {
            System.out.println(e.getMessage());
        }

    }

    private void handleLogin(String[] args)
    {
        if(args.length!=3)
        {
            System.out.println("Incorect number of paramters");
            return;

        }
        try{
            currenUserToken=authService.login(args[1],args[2]);
            if(currenUserToken!=null)
            {
                System.out.println("Logged in succesfully!");
            }else{
                System.out.println("Incorect password");
            }
        }
        catch (ServiceException e)
        {
            System.out.println(e.getMessage());
        }

    }

    private void handleLogout(String[] args)
    {
        if(args.length!=1)
        {
            System.out.println("Incorect number of paramters");
        }
        authService.logout();
        currenUserToken=null;
    }
    private void handeShowVault(String[] args)
    {
        if(args.length!=1)
        {
            System.out.println("Incorect number of paramters");
            return;
        }

        try{
            var items=vaultService.getVaultItems(currenUserToken);
            items.forEach(System.out::println);
        }
        catch (ServiceException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private void handleAddItem(String[] args)
    {
        if(args.length!=2)
        {
            System.out.println("Incorect number of paramters");
            return;
        }
        String username=authService.validateTokenAndGetUsername(currenUserToken);
        if(username==null){
            System.out.println("You must be logged in to add an item");
            return;

        }


        Scanner sc=new Scanner(System.in);
        VaultItem newItem = null;

        System.out.print("Enter title: ");
        String title=sc.nextLine().trim();
        System.out.print("Enter category (BANKING, SOCIAL, WORK, EMAIL, OTHER): ");
        String categoryStr=sc.nextLine().trim().toUpperCase();

        Category category;
        try{
            category=Category.valueOf(categoryStr);
        }catch (IllegalArgumentException e)
        {
            System.out.println("Invalid category. Defaulting to other");
            category=Category.OTHER;
        }

        switch (args[1])
        {
            case "note":
                System.out.print("Enter content :");
                String content=sc.nextLine();
                newItem=new SecureNote(title, category,"",content);
                break;
            case "card":
                System.out.print("Enter card number: ");
                String cardNo = sc.nextLine();
                System.out.print("Enter card holder name: ");
                String ownerName = sc.nextLine();
                System.out.print("Enter CVV: ");
                int cvv = 0;
                try {
                    cvv = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("CVV must be a valid number.");
                    return;
                }
                newItem = new Card(title, category, "", cardNo, ownerName, cvv);
                break;
            case "web":
                System.out.print("Enter website URL: ");
                String url = sc.nextLine();
                System.out.print("Enter username/email: ");
                String usr = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();
                newItem = new WebCredential(title, category, "", url, usr, pass);
        }
        if(newItem!=null)
        {
            vaultService.addItem(newItem,currenUserToken);
        }



    }


}
