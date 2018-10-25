package utoronto.saturn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Database {
    protected Object userName;
    protected Object password;
    protected String dbms;
    protected String serverName;
    protected String portNumber;
    protected String dbName;
    // Setup for logging
    protected Logger log = Logger.getLogger(UserDatabase.class.getName());
    protected Connection connection;

    public Database(Object password, String dbms, String dbName, String portNumber, Object userName, String serverName) {
        this.password = password;
        this.dbms = dbms;
        this.dbName = dbName;
        this.portNumber = portNumber;
        this.userName = userName;
        this.serverName = serverName;
        try {
            connection = this.getConnection();
        } catch (SQLException e) {
            log.severe("Had an SQL Exception while trying to connect to the database");
            e.printStackTrace();
        }
    }

    // CODE FROM: https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
    // ACCESSED: OCTOBER 21 2018
    protected Connection getConnection() throws SQLException {

        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        // Todo (rmartin): modify for our database
        if (this.dbms.equals("mysql")) {
            connection =
                    DriverManager.getConnection(
                            "jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/",
                            connectionProps);
        } else if (this.dbms.equals("derby")) {
            connection =
                    DriverManager.getConnection(
                            "jdbc:" + this.dbms + ":" + this.dbName + ";create=true", connectionProps);
        }
        log.fine("Connected to database");
        return connection;
    }
}
