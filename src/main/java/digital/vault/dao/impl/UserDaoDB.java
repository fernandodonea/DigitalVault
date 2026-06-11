package digital.vault.dao.impl;

import digital.vault.dao.UserDao;
import digital.vault.db.Database;
import digital.vault.exception.DatabaseException;
import digital.vault.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDB implements UserDao
{
    private Connection conn() throws SQLException{
        return Database.getInstance().getConnection();
    }



    @Override
    public void create(User u)
    {
        String sql="INSERT INTO users(id, username, email, password) VALUES (?,?,?,?)";
        try(PreparedStatement pstm=conn().prepareStatement(sql);){

            pstm.setString(1,u.getId());
            pstm.setString(2,u.getUsername());
            pstm.setString(3,u.getEmail());
            pstm.setString(4,u.getMasterPassword());

            pstm.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new DatabaseException("[Auth error]"+e.getMessage());
        }

    }
    @Override
    public List<User> findAll() {
        List<User> queryUsers=new ArrayList<>();
        try{
            String sql="SELECT * FROM USERS";
            Statement st= conn().createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                String queryId = rs.getString("id");
                String queryUsername=rs.getString("username");
                String queryEmail =rs.getString("email");
                String queryPassword =rs.getString("password");
                User u=new User(queryId,queryUsername, queryEmail, queryPassword);
                queryUsers.add(u);
            }

        }
        catch (SQLException e)
        {
            throw new DatabaseException(e.getMessage());
        }
        return queryUsers;
    }
    @Override
    public User findById(String id)
    {
        String sql="SELECT * FROM USERS WHERE ID=(?)";
        User querryUser=null;
        try(PreparedStatement pstm= conn().prepareStatement(sql);){

            pstm.setString(1,id);
            ResultSet rs= pstm.executeQuery();
            if(rs.next()){
                String queryId = rs.getString("id");
                String queryUsername=rs.getString("username");
                String queryEmail=rs.getString("email");
                String queryPassword =rs.getString("password");
                querryUser=new User(queryId, queryUsername,queryEmail, queryPassword);

            }

        }catch(SQLException e)
        {
            throw new DatabaseException(e.getMessage());
        }
        return querryUser;

    }
    @Override
    public void update(String id, User user)
    {
        String sql="UPDATE users SET username=?, email=?, password=? WHERE id=?";
        try(PreparedStatement ps=conn().prepareStatement(sql)){
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMasterPassword());
            ps.setString(4,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }


    }
    @Override
    public void deleteById(String id)
    {
        String sql="DELETE FROM users WHERE id=(?)";
        try(PreparedStatement pstm= conn().prepareStatement(sql))
        {
            pstm.setString(1,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

    }


    @Override
    public User findByUsername(String username)
    {
       String sql="SELECT * FROM users WHERE username=(?)";
       User queryUser=null;
       try(PreparedStatement pstm= conn().prepareStatement(sql))
       {
           pstm.setString(1,username);
           ResultSet rs= pstm.executeQuery();
           while(rs.next())
           {
               String queryId = rs.getString("id");
               String queryUsername=rs.getString("username");
               String queryEmail=rs.getString("email");
               String queryPassword =rs.getString("password");
               queryUser=new User(queryId, queryUsername,queryEmail, queryPassword);
           }

       } catch (SQLException e) {
           throw new DatabaseException(e.getMessage());
       }
       return queryUser;
    }
}
