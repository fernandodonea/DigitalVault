package digital.vault.model.vault;

import digital.vault.model.Category;

import java.util.UUID;

public abstract class VaultItem implements Comparable<VaultItem>
{
    private final String id;

    private String title;
    private Category category;
    private String usernameOwner;

    public VaultItem(String title, Category category, String usernameOwner)
    {
        this.id= UUID.randomUUID().toString();
        this.title = title;
        this.category = category;
        this.usernameOwner = usernameOwner;
    }

    public String getId() {return id;}
    public String getTitle() {return title;}
    public Category getCategory() {return category;}
    public String getUsernameOwner() {return usernameOwner;}

    public void setTitle(String title) {this.title = title;}
    public void setCategory(Category category) {this.category = category;}
    public void setUsernameOwner(String usernameOwner) {this.usernameOwner = usernameOwner;}


    @Override
    public String toString()
    {
        return "[item id]:"+id;
    }

    @Override
    public int compareTo(VaultItem o)
    {
        //comparam dupa tip
        int typeComparison=getItemType().compareTo(o.getItemType());
        if(typeComparison!=0)
            return typeComparison;

        //comparam dupa categorie
        int categoryCompariosn=this.getCategory().toString().compareTo(o.getCategory().toString());
        if(categoryCompariosn!=0)
            return typeComparison;

        //sortam alfabetic
        return this.getTitle().compareTo(o.getTitle());
    }

    private String getItemType()
    {
        if(this instanceof Card) return "1_card";
        if(this instanceof WebCredential) return "2_web";
        if(this instanceof SecureNote) return "3_note";

    }

}
