package utoronto.saturn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import java.sql.*;
import utoronto.saturn.UserDatabase;

public class Database {
    private final static String url = "jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn";
    private final static String username = "tjlevpcn";
    private final static String password = "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu";
    // Setup for logging
    protected Logger log = Logger.getLogger(UserDatabase.class.getName());
    protected Connection connection;
    static Statement SQLStatement;


    public Database()  throws SQLException{
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

        Connection connection = DriverManager.getConnection(url, username, password);

        this.connection = connection;
        SQLStatement = connection.createStatement();

        return connection;
    }
}
