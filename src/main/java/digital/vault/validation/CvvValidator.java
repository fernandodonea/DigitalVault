package digital.vault.validation;

import digital.vault.exception.ValidationException;

public class CvvValidator implements Validator<Integer>
{
    @Override
    public void validate(Integer value) throws ValidationException {
        if(value<100 || value>999)
            throw new ValidationException("Cvv must be a three digit number");
    }
}
