package digital.vault.util.exceptions;

public class UrlValidator implements Validator<String>
{

    @Override
    public void validate(String url) throws ValidationException
    {
        if(url==null || url.trim().isEmpty())
            throw new ValidationException("URL nu poate gi gol");

        String regexValidare = "^(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&\\/=]*)$";
        if(!url.matches(regexValidare))
            throw new ValidationException("URL nu este valid");

    }
}

