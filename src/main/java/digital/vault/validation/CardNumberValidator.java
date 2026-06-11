package digital.vault.validation;

import digital.vault.exception.ValidationException;

public class CardNumberValidator implements Validator<String>
{


    @Override
    public void validate(String cardNumber) throws ValidationException {
        if(cardNumber==null || cardNumber.trim().isEmpty())
            throw  new ValidationException("Card number cannot be empty");

        if(cardNumber.replaceAll(" ","").length()!=16)
            throw new ValidationException("Invalid card number");

        for(int i=0;i<cardNumber.length();i++)
        {
            if(!Character.isDigit(cardNumber.charAt(i)) && cardNumber.charAt(i)!=' ')
                throw new ValidationException("Card number must contain only digits and spaces");
        }

    }
}
