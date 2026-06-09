package digital.vault.util.exceptions;

public class CardNumberValidator implements Validator<String>
{


    @Override
    public void validate(String cardNumber) throws ValidationException {
        if(cardNumber==null || cardNumber.trim().isEmpty())
            throw  new ValidationException("Numarul cardului nu poate fi gol");

        for(int i=0;i<cardNumber.length();i++)
        {
            if(Character.isDigit(cardNumber.charAt(i))==false && cardNumber.charAt(i)!=' ')
                throw new ValidationException("Numarul cardului trebuie sa contina doar cifre si spatii");
        }

    }
}
