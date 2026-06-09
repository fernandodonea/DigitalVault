package digital.vault.util.exceptions;

public class CvvValidator implements Validator<Integer>
{
    @Override
    public void validate(Integer value) throws ValidationException {
        if(value<100 || value>999)
            throw new ValidationException("CVV-ul trrbuie sa fie un numar de trei cifre");
    }
}
