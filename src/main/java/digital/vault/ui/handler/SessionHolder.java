package digital.vault.ui.handler;

public class SessionHolder
{
    private String token=null;

    public String getToken(){return token;}
    public void setToken(String token){this.token=token;}
    public void clearToken(){this.token=null;}
    public boolean isLoggedIn(){return token!=null;}
}
