package digital.vault.model;

public enum Category {
    SOCIAL("Social media"),
    BANKING("Banking and finance"),
    WORK("Work and Professional"),
    EMAIL("Email"),
    SHOPPING("Shopping"),
    ENTERTAINMENT("Entertainment"),
    SECURITY("Security and privacy"),
    OTHER("OTHER");

    private final String displayName;
    Category(String displayName) {
        this.displayName = displayName;
    }

    //getteri
    public String getDisplayName() {return displayName;}

    @Override
    public String toString()
    {
        return "[CATEGORY]:"+displayName;
    }
}
