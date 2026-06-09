package digital.vault.model.vault.items;

import digital.vault.model.Category;
import digital.vault.model.VaultItem;

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
    public void displayItem()
    {
        System.out.println("===================================");
        System.out.println("Credit/Debit Card: " + getTitle());
        System.out.println("-----------------------------------");

        StringBuilder maskedCardNumber=new StringBuilder();
        for(int i=0;i<cardNumber.length();i++)
        {
            if(i<cardNumber.length()-4 || cardNumber.charAt(i)==' ')
                maskedCardNumber.append("*");
            else
                maskedCardNumber.append(cardNumber.charAt(i));
        }

        System.out.println("Card number: "+maskedCardNumber);
        System.out.println("Card holder name: "+cardHolderName);

        System.out.println("===================================");

    }
}
