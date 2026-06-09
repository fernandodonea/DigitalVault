package digital.vault.validation;

import digital.vault.exception.ValidationException;

public class UrlValidator implements Validator<String>
{

    @Override
    public void validate(String url) throws ValidationException
    {
        if(url==null || url.trim().isEmpty())
            throw new ValidationException("Website url cannot be empty ");

        String regexValidare = "^(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&\\/=]*)$";
        if(!url.matches(regexValidare))
            throw new ValidationException("Website url is not valid");

    }
}

