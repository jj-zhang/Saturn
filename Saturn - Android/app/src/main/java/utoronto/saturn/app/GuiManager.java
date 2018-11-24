package utoronto.saturn.app;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import utoronto.saturn.Event;
import utoronto.saturn.User;
import utoronto.saturn.UserDatabase;

public class GuiManager {
    // This class is the connection between the front end and the back end
    // A singleton class accessed by the front end to query for info, etc.

    private static GuiManager instance = new GuiManager();
    private User currentUser;
    private UserDatabase userDatabase;
    private static Statement SQLStatement;
    private static final String[] categories = new String[]{"Anime", "Concerts", "Movies", "Games"};

    private GuiManager() {
    }

    // Use this to get the current instance of this class
    public static GuiManager getInstance() {
        return instance;
    }

    /*
        Checks to see if the given email, is already taken
        Returns true if the sign up is successful and false otherwise
     */
    public boolean signUp(String username, String email, String password) {
        // Get the resulting relations after selecting email
        if (UserDatabase.doesEmailExist(email)) {
            return false;
        }

        // Create the new user account and add it to the users database
        User user = new User(username, email, password);
        boolean res = UserDatabase.openAccount(user);
        if (res) {
            setCurrentUser(user);
            return true;
        }
        return false;
    }

    /*
        Checks to see if the given email, and password are valid
        Returns true if the login is successful and false otherwise
     */
    public boolean logIn(String email, String password) {

        // Get the resulting relations after selecting email
        if (UserDatabase.doesEmailExist(email)) {
            try {
                // Check to see if the corresponding password matches
                ResultSet set = SQLStatement.executeQuery("SELECT " + password + " FROM users WHERE email = " + email);
                if (set.next()) {
                    String correctPassword = set.getString(1);
                    if (correctPassword.equals(password)) {
                        User loginUser = new User("username", email, password);
                        setCurrentUser(loginUser);
                        return true;
                    }
                    return false;
                } else {
                    throw new IllegalArgumentException("Current email does not have any saved passwords in the database -- This should never occur");
                }

            } catch (java.sql.SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

//    // TODO: Make use of this in log in and sign up
//    public boolean checkEmail(String email) {
//        SQLBackgroundQuery sqlBackgroundQuery = new SQLBackgroundQuery();
//        sqlBackgroundQuery.execute(String.format("SELECT * FROM Users WHERE email = '%s'", email));
//        ResultSet res;
//        try {
//            res = sqlBackgroundQuery.get();
//            return res.next();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }

    // get event functions
    public List<Event> getEventsByCategory(String category) {
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(User user) {
        currentUser = user;
        userDatabase = new UserDatabase(user);
    }

    // get suggestions based on the current user
    public List<Event> getSuggestions() {
        return null;
    }

    public static String[] getCategories() {
        return categories;
    }
}
