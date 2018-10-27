package utoronto.saturn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import java.sql.*;
import utoronto.saturn.UserDatabase;

public class Database {
    protected String userName;
    protected String password;
    protected String url;
    // Setup for logging
    protected Logger log = Logger.getLogger(UserDatabase.class.getName());
    protected Connection connection;

    public Database(String password, String userName, String url)  throws SQLException{
        this.password = password;
        this.userName = userName;
        this.url = url;
        try {
            Class.forName("org.postgresql.Driver");
            getConnection();

        }
        catch (java.lang.ClassNotFoundException e) {
            log.severe("Had an SQL Exception while trying to connect to the database");
            e.printStackTrace();
        }

    }


    protected Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(url, userName, password);

        this.connection = connection;

        return connection;
    }
}
