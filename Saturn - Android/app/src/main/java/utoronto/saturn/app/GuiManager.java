package utoronto.saturn.app;

import java.sql.SQLException;
import java.util.List;

import utoronto.saturn.Database;
import utoronto.saturn.Event;
import utoronto.saturn.User;

public class GuiManager {
    // This class is the connection between the front end and the back end
    // A singleton class accessed by the front end to query for info, etc.

    private static GuiManager instance;
    private User currentUser;
    private Database database;

    private GuiManager() throws SQLException {
        database = new Database();
        instance = new GuiManager();
    }

    // Use this to get the current instance of this class
    public static GuiManager getInstance(){
        return instance;
    }

    // sign up functions
    public boolean signUp(String email, String firstName, String lastName, String password) {
        return true;
    }

    // log in functions
    public User logIn(String email, String password) {
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
