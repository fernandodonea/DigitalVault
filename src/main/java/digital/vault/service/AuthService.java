package digital.vault.service;

import digital.vault.dao.impl.UserDaoInMemory;
import digital.vault.model.User;
import digital.vault.model.UserSession;
import digital.vault.validation.EmailValidator;

public class AuthService
{
    private UserDaoInMemory userDao;
    private UserSession currentSession;


    public AuthService(UserDaoInMemory userDao)  //injectam prin constructor
    {
        this.userDao = userDao;
    }

    public boolean register(String username, String email, String masterPassword)
    {
        if(userDao.findByUsername(username)!=null)
        {
            System.out.println("There is already a user with the username  "+username);
            return false;
        }
        else
        {
            EmailValidator emailValidator=new EmailValidator();
            emailValidator.validate(email);

            User newUser=new User(username,email,masterPassword);
            userDao.create(newUser);
            return true;
        }
    }

    //daca login-ul e corect, returnam Secret Token-ul
    public String login(String username, String password)
    {
        User user=userDao.findByUsername(username);
        if(user!=null && user.getMasterPassword().equals(password))
        {
            currentSession=new UserSession(username, 2);
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
