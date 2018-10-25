package utoronto.saturn;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDatabase {

    private Object userName;
    private Object password;
    private String dbms;
    private String serverName;
    private String portNumber;
    private String dbName;

    // Setup for logging
    private Logger log = Logger.getLogger(UserDatabase.class.getName());
    private Connection connection;
    // Todo (rmartin): Need a user here that we can get attributes for

    public UserDatabase() {
        // Todo (rmartin): Modify for our database
        super();
    }

    public UserDatabase(
            Object userName,
            Object password,
            String dbms,
            String serverName,
            String portNumber,
            String dbName) {
        this.userName = userName;
        this.password = password;
        this.dbms = dbms;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.dbName = dbName;

        log.setLevel(Level.FINE);

        // Setup Connection to our database
        try {
            connection = this.getConnection();
        } catch (SQLException e) {
            log.severe("Had an SQL Exception while trying to connect to the database");
            e.printStackTrace();
        }
    }

    // Public API

    public void leaveGlobalEvent(String eventName) {
    }

    // Todo (rmartin): Should eventually have an option of no userID here
    public void leaveGlobalEvent(String userId, String eventName) {

        String delete =
                "DELETE FROM user WHERE userId = '" + userId + "' AND eventName = '" + eventName + "';";
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Had an SQL exception while creating a statement");
            e.printStackTrace();
        }

        try {
            statement.execute(delete);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a delete statement");
            e.printStackTrace();
        }
    }

    public void joinGlobalEvent() {
        //INSERT INTO table(column1, column2, …)
        //VALUES
        // (value1, value2, …);
    }

    public void removeLocalEvent(String eventName) {
    }

    // Todo (rmartin): Should eventually have an option of no userID here
    public void removeLocalEvent(String userId, String eventName) {

        String delete =
                "DELETE FROM user WHERE userId = '" + userId + "' AND eventName = '" + eventName + "';";
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Had an SQL exception while creating a statement");
            e.printStackTrace();
        }

        try {
            statement.execute(delete);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a delete statement");
            e.printStackTrace();
        }
    }

    public void addLocalEvent() {
        //INSERT INTO table(column1, column2, …)
        //VALUES
        // (value1, value2, …);
    }

    public void modifyAttribute() {
        // DELETE THEN
        //INSERT INTO table(column1, column2, …)
        //VALUES
        // (value1, value2, …);
    }

    public void openAccount() {
        //INSERT INTO table(column1, column2, …)
        //VALUES
        // (value1, value2, …);
    }

    public ResultSet getAttribute(String attribute) {
        // Todo (rmartin): Implement when we have a user obj to use
        return null;
    }

    // Todo (rmartin): Should eventually have an option of no userID here
    public ResultSet getAttribute(String userID, String attribute) {

        ResultSet resultSet = null;

        String query = "SELECT " + attribute + " FROM user WHERE userId = '" + userID + "';";

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Had an SQL exception while creating a statement");
            e.printStackTrace();
        }

        try {
            assert statement != null;
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while trying to query an attribute");
            e.printStackTrace();
        }

        return resultSet;
    }

    // Private Code

    // CODE FROM: https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
    // ACCESSED: OCTOBER 21 2018
    private Connection getConnection() throws SQLException {

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
