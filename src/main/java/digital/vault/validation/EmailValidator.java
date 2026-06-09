package digital.vault.validation;

public class EmailValidator implements Validator<String>
{
    @Override
    public void validate(String email) throws ValidationException
    {
        if (email==null || email.isEmpty())
            throw new ValidationException("Email cant pe empty");


        String regexValidare="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(regexValidare))
            throw new ValidationException("Email is not valid");
    }
}
