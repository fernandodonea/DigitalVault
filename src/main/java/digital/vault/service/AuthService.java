package digital.vault.service;

import digital.vault.dao.UserDao;
import digital.vault.exception.ServiceException;
import digital.vault.model.User;
import digital.vault.model.UserSession;
import digital.vault.validation.EmailValidator;
import digital.vault.exception.ValidationException;
import digital.vault.validation.PasswordValidator;

public class AuthService
{
    private final UserDao userDao;
    private UserSession currentSession;


    public AuthService(UserDao userDao)  //injectam prin constructor
    {
        this.userDao = userDao;
    }

    public void register(String username, String email, String masterPassword)
    {
        if(username==null || username.isEmpty()){
            throw new ServiceException("Name cannont be empty");
        }
        if(userDao.findByUsername(username)!=null){
            throw new ServiceException("Username already taken");
        }

        try{
            new EmailValidator().validate(email);
        }
        catch (ValidationException e)
        {
            throw new ServiceException("Email is not valid"+e.getMessage());
        }


        try{
            new PasswordValidator().validate(masterPassword);
        }
        catch (ValidationException e){
            throw new ServiceException("Passowrd is not valid: "+e.getMessage());
        }

        userDao.create(new User(username,email,masterPassword));

    }

    //daca login-ul e corect, returnam Secret Token-ul
    public String login(String username, String password)
    {
        User user=userDao.findByUsername(username);
        if(user!=null && user.getMasterPassword().equals(password))
        {
            currentSession=new UserSession(username, 1);
            return currentSession.getSecretToken();
        }

        return null;
    }

    public void logout()
    {
        currentSession=null;
    }


    //metoda de securitate pentru a valida user-ul si token-ul
    public String validateTokenAndGetUsername(String token)
    {
        if(currentSession==null || token==null)
            return null;
        if(!currentSession.getSecretToken().equals(token))
            return null;
        if(currentSession.isExpired())
        {
            logout();
            return null;
        }
        return currentSession.getUsername();
    }
}
