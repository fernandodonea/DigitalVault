package digital.vault.validation;

public class CardNumberValidator implements Validator<String>
{


    @Override
    public void validate(String cardNumber) throws ValidationException {
        if(cardNumber==null || cardNumber.trim().isEmpty())
            throw  new ValidationException("Card number cannot be empty");

        for(int i=0;i<cardNumber.length();i++)
        {
            if(Character.isDigit(cardNumber.charAt(i))==false && cardNumber.charAt(i)!=' ')
                throw new ValidationException("Card number must contain only digits and spaces");
        }

    }
}
