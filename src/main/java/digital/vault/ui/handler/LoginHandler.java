package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.model.UserSession;
import digital.vault.service.AuthService;

public class LoginHandler implements CommandHandler
{
    private final AuthService authService;
    private final SessionHolder session;

    public LoginHandler(AuthService authService, SessionHolder session) {
        this.authService = authService;
        this.session = session;
    }


    @Override
    public void handle(String[] args)
    {
        if(args.length!=3)
        {
            System.out.println("Usage: login <username> <password>");
            return;
        }
        try{
            String token=authService.login(args[1],args[2]);
            session.setToken(token);
            System.out.println("Logged in succesfully");
        }
        catch (ServiceException s)
        {
            System.out.println("Loggin failed: "+s.getMessage());
        }
    }
}
