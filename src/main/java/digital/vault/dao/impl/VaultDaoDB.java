package digital.vault.dao.impl;

import digital.vault.dao.VaultDao;
import digital.vault.db.Database;
import digital.vault.exception.DatabaseException;
import digital.vault.model.Category;
import digital.vault.model.vault.Card;
import digital.vault.model.vault.SecureNote;
import digital.vault.model.vault.VaultItem;
import digital.vault.model.vault.WebCredential;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaultDaoDB implements VaultDao
{

    private Connection conn() throws SQLException {
        return Database.getInstance().getConnection();
    }


    @Override
    public void create(VaultItem item)
    {
        String sql= """
        INSERT INTO vault_items (id, type, title, category, username_owner,
                                 content,
                                 url, cred_username, password,
                                 card_number, card_holder, cvv)
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        try(PreparedStatement pstm=conn().prepareStatement(sql);)
        {
            pstm.setString(1, item.getId());
            pstm.setString(3,item.getTitle());
            pstm.setString(4,item.getCategory().name());
            pstm.setString(5,item.getUsernameOwner());
            
            if(item instanceof SecureNote note) {
                pstm.setString(2,"note");

                pstm.setString(6,note.getContent());

                pstm.setNull(7, Types.VARCHAR);
                pstm.setNull(8, Types.VARCHAR);
                pstm.setNull(9, Types.VARCHAR);

                pstm.setNull(10, Types.VARCHAR);
                pstm.setNull(11, Types.VARCHAR);
                pstm.setNull(12, Types.INTEGER);
            }else if(item instanceof WebCredential web)
            {
                pstm.setString(2,"web");

                pstm.setNull(6, Types.VARCHAR);

                pstm.setString(7,web.getUrl());
                pstm.setString(8,web.getUsername());
                pstm.setString(9,web.getPassword());

                pstm.setNull(10, Types.VARCHAR);
                pstm.setNull(11, Types.VARCHAR);
                pstm.setNull(12, Types.INTEGER);

            } else if (item instanceof Card card)
            {
                pstm.setString(2,"card");

                pstm.setNull(6, Types.VARCHAR);

                pstm.setNull(7, Types.VARCHAR);
                pstm.setNull(8, Types.VARCHAR);
                pstm.setNull(9, Types.VARCHAR);

                pstm.setString(10, card.getCardNumber());
                pstm.setString(11,card.getCardHolderName());
                pstm.setInt(12, card.getCvv());

            }

            pstm.executeUpdate();


        }catch (SQLException e)
        {
            throw new DatabaseException(e.getMessage());
        }


    }
    @Override
    public List<VaultItem> findAll()
    {
        List<VaultItem> vaultItems=new ArrayList<>();
        String sql="SELECT * FROM vault_items";
        try(Statement st=conn().createStatement())
        {
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                String id=rs.getString("id");
                String title=rs.getString("title");
                String type=rs.getString("type");
                Category category=Category.valueOf(rs.getString("category"));
                String owner=rs.getString("username_owner");

                switch (type){
                    case "note":
                        vaultItems.add(new SecureNote(id,title, category, owner,
                                rs.getString("content")));break;
                    case "web":
                        vaultItems.add(new WebCredential(id,title,category,owner,
                                rs.getString("url"),
                                rs.getString("cred_username"),
                                rs.getString("password")));break;
                    case "card":
                        vaultItems.add(new Card(id,title,category,owner,
                                rs.getString("card_number"),
                                rs.getString("card_holder"),
                                rs.getInt("cvv")));break;

                }
            }

        }catch (SQLException e)
        {
            throw new DatabaseException(e.getMessage());
        }
        return vaultItems;
    }
    @Override
    public VaultItem findById(String queryId)
    {
        VaultItem item=null;
        String sql="SELECT * FROM vault_items WHERE id=(?)";
        try(PreparedStatement pstm= conn().prepareStatement(sql))
        {
            pstm.setString(1,queryId);
            ResultSet rs=pstm.executeQuery();
            if(rs.next())
            {
                String id=rs.getString("id");
                String title=rs.getString("title");
                String type=rs.getString("type");
                Category category=Category.valueOf(rs.getString("category"));
                String owner=rs.getString("username_owner");

                switch (type) {
                    case "note":
                        item = new SecureNote(id, title, category, owner,
                                rs.getString("content"));break;
                    case "web":
                        item = new WebCredential(id, title, category, owner,
                                rs.getString("url"),
                                rs.getString("cred_username"),
                                rs.getString("password"));break;
                    case "card":
                        item = new Card(id, title, category, owner,
                                rs.getString("card_number"),
                                rs.getString("card_holder"),
                                rs.getInt("cvv"));break;
                }
            }

        }
        catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        }


        return item;
    }
    @Override
    public void update(String id, VaultItem updatedObject)
    {
        String sql= """
        UPDATE vault_items SET
            title=?, category=?,
            content=?,
            url=?,cred_username=?,password=?,
            card_number=?, card_holder=?, cvv=?
        WHERE id=?
        """;
        try(PreparedStatement pstm= conn().prepareStatement(sql))
        {
            pstm.setString(1,updatedObject.getTitle());
            pstm.setString(2, updatedObject.getCategory().name());

            if(updatedObject instanceof SecureNote note) {
                pstm.setString(3,"note");

                pstm.setString(4,note.getContent());
                pstm.setNull(5, Types.VARCHAR);
                pstm.setNull(6, Types.VARCHAR);

                pstm.setNull(7, Types.VARCHAR);
                pstm.setNull(8, Types.VARCHAR);
                pstm.setNull(12, Types.INTEGER);
            }else if(updatedObject instanceof WebCredential web)
            {

                pstm.setNull(3, Types.VARCHAR);

                pstm.setString(4,web.getUrl());
                pstm.setString(5,web.getUsername());
                pstm.setString(6,web.getPassword());

                pstm.setNull(7, Types.VARCHAR);
                pstm.setNull(8, Types.VARCHAR);
                pstm.setNull(9, Types.INTEGER);

            } else if (updatedObject instanceof Card card)
            {

                pstm.setNull(3, Types.VARCHAR);

                pstm.setNull(4, Types.VARCHAR);
                pstm.setNull(5, Types.VARCHAR);
                pstm.setNull(6, Types.VARCHAR);

                pstm.setString(7, card.getCardNumber());
                pstm.setString(8,card.getCardHolderName());
                pstm.setInt(9, card.getCvv());

            }
            pstm.setString(10, id);
            pstm.executeUpdate();

        }catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    @Override
    public void deleteById(String id)
    {
        String sql="DELETE FROM vault_items WHERE id=(?)";
        try(PreparedStatement pstm= conn().prepareStatement(sql))
        {
            pstm.setString(1,id);;
            pstm.executeUpdate();

        }catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        }
    }




    @Override
    public List<VaultItem> findUserItems(String username)
    {
        List<VaultItem> usersVaultItems=new ArrayList<>();
        String sql="SELECT * FROM vault_items WHERE username_owner=(?)";

        try(PreparedStatement pstm= conn().prepareStatement(sql))
        {
            pstm.setString(1,username);
            ResultSet rs=pstm.executeQuery();

            while(rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String type = rs.getString("type");
                Category category = Category.valueOf(rs.getString("category"));
                String owner = rs.getString("username_owner");

                switch (type) {
                    case "note":
                        usersVaultItems.add(new SecureNote(id, title, category, owner,
                                rs.getString("content")));
                        break;
                    case "web":
                        usersVaultItems.add(new WebCredential(id, title, category, owner,
                                rs.getString("url"),
                                rs.getString("cred_username"),
                                rs.getString("password")));
                        break;
                    case "card":
                        usersVaultItems.add(new Card(id, title, category, owner,
                                rs.getString("card_number"),
                                rs.getString("card_holder"),
                                rs.getInt("cvv")));
                        break;

                }
            }

        }catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        }
        return usersVaultItems;
    }
}
