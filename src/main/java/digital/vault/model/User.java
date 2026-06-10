package digital.vault.model;

import java.util.UUID;

public class User
{
    private final String id;

    private String username;
    private String email;
    private String masterPassword;


    public User(String username, String email, String masterPassword)
    {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.masterPassword = masterPassword;
    }

    //getteri
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getId() {return id;}
    public String getMasterPassword() {return masterPassword;}

    //setteri
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setMasterPassword(String masterPassword) {this.masterPassword = masterPassword;}
}
