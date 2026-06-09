package digital.vault.validation;

public class CvvValidator implements Validator<Integer>
{
    @Override
    public void validate(Integer value) throws ValidationException {
        if(value<100 || value>999)
            throw new ValidationException("Cvv must be a three digit number");
    }
}
