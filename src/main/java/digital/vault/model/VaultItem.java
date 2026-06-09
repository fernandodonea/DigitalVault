package digital.vault.model;

public abstract class VaultItem implements Comparable<VaultItem>
{
    private final int id;
    private static int idCounter=0;

    private String title;
    private Category category;
    private String usernameOwner;

    public VaultItem(String title, Category category, String usernameOwner)
    {
        this.id=idCounter++;
        this.title = title;
        this.category = category;
        this.usernameOwner = usernameOwner;
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public Category getCategory() {return category;}
    public String getUsernameOwner() {return usernameOwner;}

    public void setTitle(String title) {this.title = title;}
    public void setCategory(Category category) {this.category = category;}
    public void setUsernameOwner(String usernameOwner) {this.usernameOwner = usernameOwner;}


    public abstract void displayItem();

    @Override
    public int compareTo(VaultItem o)
    {
        return this.getTitle().compareTo(o.getTitle());
    }

}
