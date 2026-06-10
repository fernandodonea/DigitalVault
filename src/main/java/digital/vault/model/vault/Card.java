package digital.vault.model.vault;

import digital.vault.model.Category;

public class Card extends VaultItem
{
    private String cardNumber;
    private String cardHolderName;
    private int cvv;

    public Card(String title, Category category, String usernameOwner, String cardNumber, String cardHolderName, int cvv) {
        super(title, category, usernameOwner);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    public String getCardNumber() {return cardNumber;}
    public String getCardHolderName() {return cardHolderName;}
    public int getCvv() {return cvv;}

    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}
    public void setCardHolderName(String cardHolderName) {this.cardHolderName = cardHolderName;}
    public void setCvv(int cvv) {this.cvv = cvv;}


    @Override
    public String toString()
    {
        return super.toString()+" [card]"+ "       "+getTitle()+"      "+getCategory();
    }
}
