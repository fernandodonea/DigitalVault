package digital.vault.model;

public class User
{
    private final int id;
    private static int idCounter=0;

    private String username;
    private String email;
    private String masterPassword;


    public User(String username, String email, String masterPassword)
    {
        this.id = idCounter++;
        this.username = username;
        this.email = email;
        this.masterPassword = masterPassword;
    }

    //getteri
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public int getId() {return id;}
    public String getMasterPassword() {return masterPassword;}

    //setteri
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setMasterPassword(String masterPassword) {this.masterPassword = masterPassword;}
}
