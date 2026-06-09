package digital.vault.ui;

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
        }
        else{
            authService.register(args[1],args[2],args[3]);
        }

    }

    private void handleLogin(String[] args)
    {
        if(args.length==3)
        {
            currenUserToken=authService.login(args[1],args[2]);
        }
        else
        {
            System.out.println("Incorect number of paramters");
        }

        if(currenUserToken!=null)
        {
            System.out.println("Succesful login!");
        }
        else
        {
            System.out.println("Incorect Data");
        }
    }

    private void handleLogout(String[] args)
    {
        if(args.length==1)
        {
            authService.logout();
            currenUserToken=null;
        }
        else
        {
            System.out.println("Incorect number of paramters");
        }
    }


}
