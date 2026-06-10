package digital.vault.ui.handler;

import digital.vault.service.AuthService;

public class LogoutHandler implements CommandHandler
{
    private final AuthService authService;
    private final SessionHolder session;

    public LogoutHandler(AuthService authService, SessionHolder session) {
        this.authService = authService;
        this.session = session;
    }

    @Override
    public void handle(String[] args) {
        if(args.length!=1)
        {
            System.out.println("Usage: logout");
            return;
        }

        if(!session.isLoggedIn())
        {
            System.out.println("You are not logged in logout");
            return;
        }

        try{
            authService.logout();
            session.clearToken();
            System.out.println("Logged out succesfully");
        }
        catch (SecurityException e)
        {
            System.out.println("Logout failed:"+e.getMessage());
        }


    }
}

