package model;

import java.util.Date;

public class CreditCard extends VaultItem
{
    private String number;
    private String cardHolderName;
    private String cvv;
    private Date expireDate;

    public CreditCard(int id, String name, String notes, Category category, String number, String cardHolderName, String cvv, Date expireDate)
    {
        super(id, name, notes, category);
        this.number = number;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expireDate = expireDate;
    }

    //getteri
    public String getNumber(){return number;}
    public String getCardHolderName(){return cardHolderName;}
    public String getCvv(){return cardHolderName;}
    public Date getExpireDate(){return expireDate;}

    //setteri
    public void setNumber(String number){this.number=number;}
    public void setCardHolderName(String cardHolderName){this.cardHolderName=cardHolderName;}
    public void setCvv(String cvv){this.cvv=cvv;}
    public void setExpireDate(Date expireDate){this.expireDate=expireDate;}

    @Override
    public String toString()
    {
        return "Credit Card:" +getId();
    }

    @Override
    public void displayItemDetails()
    {
        System.out.println("[Credit card]");
        System.out.println("Card Holder: "+cardHolderName);

        String maskedCardnumber="**** **** **** **** ";
        int n=number.length();
        maskedCardnumber+=number.substring(n-5,n);

        System.out.println("Card number: "+maskedCardnumber);
    }
}
