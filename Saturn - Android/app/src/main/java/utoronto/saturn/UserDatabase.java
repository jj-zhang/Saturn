package utoronto.saturn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class UserDatabase extends Database {

    private Object userName;
    private Object password;
    private String dbms;
    private String serverName;
    private String portNumber;
    private String dbName;
    private static final String table = "users";

    // Setup for logging
    private Logger log = Logger.getLogger(UserDatabase.class.getName());
    private Connection connection;
    private User user;
    private final static String usersColumn = "(email, username, password, eventid)";
    private final static ArrayList<String> usersValues = new ArrayList<String>(Arrays.asList("email", "username", "password", "eventid", "*"));


    public UserDatabase(User user) {
        super();
        this.user = user;
    }

    /**
     * Deletes  an event of this user with eventId (from events table)
     *
     * @param eventId the eventId in events table
     * @return true on success
     */
    public boolean leaveEvent(int eventId) {
        return leaveEvent(user.getEmail(), eventId);
    }

    /**
     * Deletes a row from users table with userId (email) and eventId (from events table)
     *
     * @param userId the user's email in users table
     * @param eventId the eventId in events table
     * @return true on success
     */
    private boolean leaveEvent(String userId, int eventId) {
        return DatabaseUtilities.deleteRow(table, "eventid", userId);
    }

    /**
     * Adds a row in users to create an entry to associate the user to event
     *
     * @param eventId the eventId in events table
     * @return true on success
     */
    public boolean joinEvent(int eventId) {
        return DatabaseUtilities.addRowUser(user.getEmail(), user.getUsername(), user.getPassword(), eventId);
    }

    /**
     * Adds a row in users to create an entry with no eventId
     *
     * @return true on success
     */
    public boolean openAccount() {
        // New account -> EventID == -1
        if(!doesEmailExist(user.getEmail())) {
            return DatabaseUtilities.addRowUser(user.getEmail(), user.getUsername(), user.getPassword(), -1);
        } else {
            return false;
        }
    }

    /**
     * Returns an arrayList of all emails in users table
     *
     * @return ArrayList<String> of all emails in users table
     */
    public ArrayList<String> getAllEmail() {
        ArrayList<String> lst = new ArrayList<>();
        ResultSet set = DatabaseUtilities.selectColumn("users", "email");
        try {
            while (set.next()) {
                lst.add(set.getString(1));
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }

    /**
     * Returns if input email in the users database
     *
     * @return true if input email is in the database
     */
    public boolean checkEmail(String email) {
        ArrayList<String> lst = getAllEmail();
        return lst.contains(email);
    }

    /**
     * Returns if input email in the users database
     *
     * @return true if input email is in the database
     */
    public boolean doesEmailExist(String email) {
        return DatabaseUtilities.selectRow(table, "email", "email", email) != null;
    }

    /**
     * Return info about a particular attribute in users table about user
     *
     * @return ResultSet that contains info about an attribute of user
     */
    public ResultSet getAttribute(String attribute) {
        return getAttribute(user.getEmail(), attribute);
    }

    private ResultSet getAttribute(String userID, String attribute) {
        return DatabaseUtilities.selectRow(table, attribute, "email", user.getEmail());
    }
}



