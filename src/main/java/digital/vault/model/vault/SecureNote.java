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
    public void displayItem()
    {
        System.out.println("===================================");
        System.out.println("Secure note"+getTitle());
        System.out.println("-----------------------------------");

        System.out.println("Note content: ");
        System.out.println(content);

        System.out.println("===================================");

    }

    @Override
    public String toString()
    {
        return "[secure note]"+"       "+getTitle()+"      "+getCategory();
    }
}
