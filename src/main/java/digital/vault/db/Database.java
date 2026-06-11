package digital.vault.db;

import digital.vault.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private final String URL="jdbc:postgresql://localhost:5432/digital_vault";
    private final String USER="vault_user";
    private final String PASS="vault_pass";

    private static Database instance;
    private Connection connection;

    private Database()
    {
        try{
            this.connection=DriverManager.getConnection(URL,USER,PASS);
        }catch (SQLException e){
            throw new DatabaseException("Cannot connect to database: "+e.getMessage());
        }

    }

    public static Database getInstance()
    {
        if(instance==null) {
            instance=new Database();
        }
        return instance;
    }
    public Connection getConnection()
    {
        try{
            if(connection==null || connection.isClosed()){
                connection = DriverManager.getConnection(URL, USER, PASS);
            }
        }catch (SQLException e){
            throw new DatabaseException("Databse connection lost: "+e.getMessage());
        }
        return connection;
    }
}
