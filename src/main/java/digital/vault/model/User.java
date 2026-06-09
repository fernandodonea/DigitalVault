package digital.vault.model;

public class User
{
    private int id;
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
}
