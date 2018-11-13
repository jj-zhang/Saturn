package utoronto.saturn;

import org.junit.Assert;
import org.junit.Test;

public class TestExistence {
    private static final String PATH = "utoronto.saturn.";

    private void assertClassExists(String path) {
        try {
            Class.forName(path);
        } catch (ClassNotFoundException e) {
            Assert.fail(path + " does not exist!");
        }
    }

    @Test
    public void checkEventExists() {
        assertClassExists(PATH + "Event");
    }

    @Test
    public void checkUserExists() {
        assertClassExists(PATH + "User");
    }

    @Test
    public void checkEventManagerExists() {
        assertClassExists(PATH + "EventManager");
    }

    @Test
    public void checkLocalEventManagerExists() {
        assertClassExists(PATH + "LocalEventManager");
    }

    @Test
    public void checkGlobalEventManagerExists() {
        assertClassExists(PATH + "GlobalEventManager");
    }

    @Test
    public void checkDatabaseClassExists() {
        assertClassExists(PATH + "Database");
    }

    @Test
    public void checkEventDatabaseClassExists() {
        assertClassExists(PATH + "EventDatabase");
    }

    @Test
    public void checkUserDatabaseClassExists() {
        assertClassExists(PATH + "UserDatabase");
    }

    @Test
    public void checkDatabaseUtilitiesClassExists() {
        assertClassExists(PATH + "DatabaseUtilities");
    }
}
