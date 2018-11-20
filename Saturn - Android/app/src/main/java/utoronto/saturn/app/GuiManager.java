package utoronto.saturn.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;

import utoronto.saturn.Database;
import utoronto.saturn.DatabaseUtilities;
import utoronto.saturn.Event;
import utoronto.saturn.User;
import utoronto.saturn.UserDatabase;

public class GuiManager {
    // This class is the connection between the front end and the back end
    // A singleton class accessed by the front end to query for info, etc.

    private static GuiManager instance;
    private User currentUser;
    private Database database;
    private static Statement SQLStatement;
    private GuiManager() throws SQLException {
        database = new Database();
        instance = new GuiManager();
    }

    // Use this to get the current instance of this class
    public static GuiManager getInstance(){
        return instance;
    }

    // sign up functions
    public static boolean signUp(String email, String firstName, String lastName, String password) {
        User loginUser = new User("username", email, password);
        UserDatabase userDB = null;

        userDB = new UserDatabase(loginUser);

        // Get the resulting relations after selecting email
        if (userDB.doesEmailExist(email)) {
            return false;
        }

        userDB.openAccount();
        return true;
    }

    // log in functions
    public static User logIn(String email, String password) {
        // TODO: Implement login checking stuff
        User loginUser = new User("username", email, password);
        UserDatabase userDB = null;

        userDB = new UserDatabase(loginUser);

        // Get the resulting relations after selecting email
        if (userDB.doesEmailExist(email)) {
            try {
                // Check to see if the corresponding password matches
                ResultSet set = SQLStatement.executeQuery("SELECT " + password + " FROM users WHERE email = " + email);
                if (set.next()) {
                    String correctPassword = set.getString(1);
                    if (correctPassword.equals(password)) {
                        return loginUser;
                    }
                    return null;
                } else {
                    throw new IllegalArgumentException("Current email does not have any saved passwords in the database -- This should never occur");
                }

            }
            catch (java.sql.SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    // get event functions
    public List<Event> getEventsByCategory(String category) {
        return null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    // get suggestions based on the current user
    public List<Event> getSuggestions() {
        return null;
    }
}
