package model;

public class SecureNote extends VaultItem
{
    private String content;


    //contructor
    public SecureNote(int id, String name, String notes, Category category, String content)
    {
        super(id, name, notes, category);
        this.content = content;
    }

    //getter
    public String getContent(){return content;}

    //setter
    public void setContent(String content){this.content=content;}

    @Override
    public void displayItemDetails()
    {
        System.out.println("[Secure note]");
        System.out.println("Content:" +content);
    }
}
