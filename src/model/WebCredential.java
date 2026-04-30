package model;

public class WebCredential extends VaultItem
{
    private String username;
    private String password;
    private String url;


    //constructor
    public WebCredential(int id, String name, String notes, Category category, String username, String password, String url)
    {
        super(id, name, notes, category);
        this.username = username;
        this.password = password;
        this.url = url;
    }

    //getteri
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getUrl() {return url;}

    //setteri
    public void setUsername(String username){this.username=username;}
    public void setPassword(String password){this.password=password;}
    public void setUrl(String url){this.url=url;}



    @Override
    public void displayItemDetails()
    {
        System.out.println("[Web credential] | Url:" +url + "|| Username"+username);
    }
}
