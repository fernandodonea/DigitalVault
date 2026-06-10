package digital.vault.model.vault;

import digital.vault.model.Category;

public class SecureNote extends VaultItem
{
    private String content;

    public SecureNote(String title, Category category, String usernameOwner, String content) {
        super(title, category, usernameOwner);
        this.content = content;
    }

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}


    @Override
    public String toString()
    {
        return super.toString()+"        [secure note]      "+getCategory()+"       [title]:"+getTitle();


    }
}
