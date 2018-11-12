package utoronto.saturn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class UserDatabase extends Database {

    private Object userName;
    private Object password;
    private String dbms;
    private String serverName;
    private String portNumber;
    private String dbName;

    // Setup for logging
    private Logger log = Logger.getLogger(UserDatabase.class.getName());
    private Connection connection;
    private User user;

    public UserDatabase(User user, String password, String userName, String url) throws SQLException {
        super(password, userName, url);
        this.user = user;
    }

    // Public API
    public void leaveGlobalEvent(String eventName) {
        leaveGlobalEvent(user.getEmail(), eventName);
    }

    private void leaveGlobalEvent(String userId, String eventName) {

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
            assert statement != null;
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a delete statement");
            e.printStackTrace();
        }
    }

    public void joinGlobalEvent(int id) {

        String insert =
                "INSERT INTO users(password, email, eventid, username) VALUES ('" + user.getPassword() + "','" + user.getEmail() + "','" + id + "', '" + user.getUsername() + "')";
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Had an SQL exception while creating a statement");
            e.printStackTrace();
        }

        try {
            assert statement != null;
            statement.executeUpdate(insert);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a insert statement");
            e.printStackTrace();
        }
    }

    public void removeLocalEvent(String eventName) {
        removeLocalEvent(user.getEmail(), eventName);
    }


    private void removeLocalEvent(String userId, String eventName) {

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
            assert statement != null;
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a delete statement");
            e.printStackTrace();
        }
    }

    public void addLocalEvent(int id) {

        String insert =
                "INSERT INTO users(password, email, eventid, username) VALUES ('" + user.getPassword() + "','" + user.getEmail() + "','" + id + "', '" + user.getUsername() + "')";
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Had an SQL exception while creating a statement");
            e.printStackTrace();
        }

        try {
            assert statement != null;
            statement.execute(insert);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a insert statement");
            e.printStackTrace();
        }
    }

    public void openAccount() {

        String insert =
                "INSERT INTO users(password, email, eventid, username) VALUES ('" + user.getPassword() + "','" + user.getEmail() + "','" + null + "', '" + user.getUsername() + "')";
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Had an SQL exception while creating a statement");
            e.printStackTrace();
        }

        try {
            assert statement != null;
            statement.executeUpdate(insert);
        } catch (SQLException e) {
            log.severe("Had an SQL exception while executing a insert statement");
            e.printStackTrace();
        }
    }

    public ResultSet getAttribute(String attribute) {
        return getAttribute(user.getEmail(), attribute);
    }

    private ResultSet getAttribute(String userID, String attribute) {

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
}
