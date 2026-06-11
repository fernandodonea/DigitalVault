package digital.vault.ui;

import digital.vault.factory.VaultItemFactory;
import digital.vault.service.AuthService;
import digital.vault.service.VaultService;
import digital.vault.ui.handler.*;

import java.util.HashMap;
import java.util.Map;

public class TerminalService
{
    private final AuthService authService;
    private final VaultService vaultService;
    private final SessionHolder session;

    private final InputReader reader;

    private final Map<String, CommandHandler> handlers;


    public TerminalService(VaultService vaultService, AuthService authService) {
        this.vaultService = vaultService;
        this.authService = authService;

        this.reader=new InputReader();
        this.session=new SessionHolder();

        this.handlers=new HashMap<>();

        VaultItemFactory factory=new VaultItemFactory(reader);

        handlers.put("register", new RegisterHandler(authService));
        handlers.put("login", new LoginHandler(authService, session));
        handlers.put("logout", new LogoutHandler(authService, session));
        handlers.put("ls", new ShowVaultHandler(vaultService, session));
        handlers.put("touch",new AddItemHandler(vaultService, factory, session));
        handlers.put("cat", new ShowHandler(vaultService, session));
        handlers.put("reveal", new RevealHandler(vaultService, authService, session, reader));
        handlers.put("nano", new UpdateHandler(vaultService, session, reader));
        handlers.put("rm", new DeleteHandler(vaultService,session));
        handlers.put("help", new HelpHandler());

    }

    public void start()
    {
        System.out.println("[Digital Vault 1.0]");
        System.out.println("Type 'help' for a list of commmands");

        while(true)
        {
            printPrompt();

            String input= reader.readLine("");

            String[] args=input.split(" ");
            String command=args[0];

            CommandHandler handler= handlers.get(command);
            if(handler!=null)
            {
                handler.handle(args);
            }
            else{
                if(command.equals("quit"))
                    return;
                System.out.println("Unknown comand. Type 'help' to see available commands");
            }

        }
    }

    private void printPrompt()
    {
        if(!session.isLoggedIn())
            System.out.print("vault> ");
        else{
            String username=authService.getUsernameFromToken(session.getToken());
            System.out.print(username+"@vault> ");
        }
    }
}