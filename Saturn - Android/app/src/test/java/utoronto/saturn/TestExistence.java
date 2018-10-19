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
    public void checkEventManagerExists() {
        assertClassExists(PATH + "EventManager");
    }

    @Test
    public void checkLocalEventManagerExists() {
        assertClassExists(PATH + "LocalEventManager");
    }

    @Test
    public void checkEventExists() {
        assertClassExists(PATH + "Event");
    }

    @Test
    public void checkGlobalEventExists() {
        assertClassExists(PATH + "GlobalEventManager");
    }
}
