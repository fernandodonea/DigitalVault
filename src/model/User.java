package model;

import java.util.Date;

public class User
{
    private String username;
    private String email;
    private String masterPassword;
    private Date createdAt;
    private boolean isActive;

    public User()
    {
        this.createdAt=new Date();
        this.isActive=true;
    }

    public User(String username, String email, String masterPassword)
    {
        this();
        this.username = username;
        this.email = email;
        this.masterPassword = masterPassword;
    }

    //getteri
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public String getMasterPassword(){return masterPassword;}
    public Date getCreatedAt(){return createdAt;}
    public boolean getIsActive(){return isActive;}

    //seteri
    public void setUsername(String username){this.username=username;}
    public void setEmail(String email){this.email=email;}
    public void setMasterPassword(String masterPassword){this.masterPassword=masterPassword;}
    public void setCreatedAt(Date createdAt){this.createdAt=createdAt;}
    public void setActive(boolean isActive){this.isActive=isActive;}

    @Override
    public String toString()
    {
        return "Username: "+username+" " +
                " | " + "email: "+email+
                " | is active?"+isActive;
    }


}
