package digital.vault.validation;

import digital.vault.exception.ValidationException;

public class PasswordValidator implements Validator<String>
{
    @Override
    public void validate(String password) throws ValidationException {
        if(password==null || password.isEmpty()){
            throw new ValidationException("Password cannot pe empty");
        }
        if(password.length()<8){
            throw new ValidationException("Password should be at least 8 characters long");
        }
    }
}
