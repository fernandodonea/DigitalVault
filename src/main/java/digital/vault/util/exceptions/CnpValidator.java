package digital.vault.util.exceptions;


import java.io.IOException;

public class CnpValidator implements Validator<String>
{
    @Override
    public void validate(String cnp) throws ValidationException
    {
        if(cnp==null || cnp.length()!=13)
            throw new ValidationException("CNP-ul trebuie sa aiba exact 13 cifre");

        for(int i=0;i<cnp.length();i++)
        {
            if(Character.isDigit(cnp.charAt(i))==false)
                throw new ValidationException("CNP-ul trebuie sa fie format doar din cifre");
        }
    }
}
