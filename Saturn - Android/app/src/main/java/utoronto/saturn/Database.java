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


    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
