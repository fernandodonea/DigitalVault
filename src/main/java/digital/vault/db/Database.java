package digital.vault.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private final String url="jdbc:postgresql://localhost:5432/digital_vault";
    private final String user="vault_user";
    private final String password="vault_pass";

    private static Database instance;
    private Connection connection;

    private Database() throws SQLException
    {
        this.connection=DriverManager.getConnection(url,user,password);
    }

    public static Database getInstance() throws SQLException
    {
        if(instance==null) {
            instance=new Database();
        }
        return instance;
    }
    public Connection getConnection() {return connection;}
}
