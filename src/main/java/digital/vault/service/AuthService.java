package digital.vault.service;

import digital.vault.dao.UserDao;
import digital.vault.exception.ServiceException;
import digital.vault.model.User;
import digital.vault.model.UserSession;
import digital.vault.validation.EmailValidator;
import digital.vault.exception.ValidationException;
import digital.vault.validation.PasswordValidator;
import org.mindrot.jbcrypt.BCrypt;

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
            throw new ServiceException(e.getMessage());
        }


        try{
            new PasswordValidator().validate(masterPassword);
        }
        catch (ValidationException e){
            throw new ServiceException("Passowrd is not valid: "+e.getMessage());
        }

        //pentru hasharea parolei
        String hashedPassword = BCrypt.hashpw(masterPassword, BCrypt.gensalt());
        userDao.create(new User(username, email, hashedPassword));

        userDao.create(new User(username,email,masterPassword));
        AuditService.getInstance().log("register");

    }

    //daca login-ul e corect, returnam Secret Token-ul
    public String login(String username, String password)
    {
        //verificam ca user-ul ca un user sa nu fie deja logat
        if(currentSession!=null && !currentSession.isExpired()){
            throw new ServiceException("Already logged as"+currentSession.getUsername()+". Logout first");
        }


        //verificam credentiasl
        User user=userDao.findByUsername(username);
        if(user==null || BCrypt.checkpw(password, user.getMasterPassword())){
            throw  new ServiceException("Invalid username or password");
        }

        //cream sesiunea
        currentSession=new UserSession(username, 1);
        AuditService.getInstance().log("login");
        return currentSession.getSecretToken();

    }

    public void logout()
    {
        if(currentSession==null){
            throw new ServiceException("No active session to logut from");
        }
        AuditService.getInstance().log("logout");
        currentSession=null;

    }


    //metoda de securitate pentru a valida user-ul si token-ul
    public String getUsernameFromToken(String token)
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
