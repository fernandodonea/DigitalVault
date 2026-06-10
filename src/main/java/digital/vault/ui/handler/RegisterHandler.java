package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.service.AuthService;

public class RegisterHandler implements CommandHandler
{
    private final AuthService authService;

    public RegisterHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void handle(String[] args) {
        if(args.length!=4){
            System.out.println("Usage: register <username> <email> <password>");
            return;
        }

        try{
            authService.register(args[1],args[2],args[3]);
            System.out.println("Account created succesfully");
        }
        catch (ServiceException s)
        {
            System.out.println("Registration failed: "+s.getMessage());
        }

    }
}
