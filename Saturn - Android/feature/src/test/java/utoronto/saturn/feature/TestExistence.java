package utoronto.saturn.feature;

import org.junit.Assert;
import org.junit.Test;

public class TestExistence {
    private static final String PATH = "utoronto.saturn.feature.";

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
    public void checkLocalEventExists() {
        assertClassExists(PATH + "LocalEvent");
    }
}
