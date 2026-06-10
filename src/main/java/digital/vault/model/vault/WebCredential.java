package digital.vault.model.vault;

import digital.vault.model.Category;

public class WebCredential extends VaultItem
{
    private String url;
    private String username;
    private String password;

    public WebCredential(String title, Category category, String usernameOwner, String url, String username, String password) {
        super(title, category, usernameOwner);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {return url;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public void setUrl(String url) {this.url = url;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}


    @Override
    public String toString()
    {
        return super.toString()+"      [web credential]     "+getCategory()+"       [title]:"+getTitle();
    }

}
