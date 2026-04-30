package model;

import java.util.Date;

public abstract class VaultItem implements Comparable<VaultItem>
{
    private int id;
    private String name;
    private String notes;
    private Category category;
    private Date createdAt;


    //constructor
    public VaultItem(int id, String name, String notes, Category category) {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.category = category;
        this.createdAt = new Date();
    }

    //getteri
    public int getId() {return id;}
    public String getName() {return name;}
    public String getNotes() {return notes;}
    public Category getCategory() {return category;}
    public Date getCreatedAt() {return createdAt;}

    //setteri
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setNotes(String notes) {this.notes = notes;}
    public void setCategory(Category category) {this.category = category;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    //metode abstracte
    public abstract void displayItemDetails();

    @Override
    public int compareTo(VaultItem other)
    {
        return this.name.compareToIgnoreCase(other.name);
    }
}
