package utoronto.saturn.feature;

import org.junit.Assert;
import org.junit.Test;

public class TestImplementsEvent {
    private static final Event LE = new Event(null, null, null, 0, 0, null);
    private static final LocalEventManager LEM = new LocalEventManager();

    private static void assertImplements(Object instObj, Class<?> obj) {
        try {
            Class<?> test = Class.forName(instObj.getClass().getCanonicalName());
        } catch(ClassNotFoundException e) {
            Assert.fail(instObj.getClass().getSimpleName() + " does not implement " + obj.getSimpleName() + "!");
        }
    }

    private static void assertImplementsMethod(Object obj, Class[] cArgs, String method) {
        try {
            obj.getClass().getMethod(method, cArgs);
        } catch(NoSuchMethodException e) {
            Assert.fail(obj.getClass().getSimpleName() + " does not implement " + method + "!");
        }
    }

    @Test
    public void testLocalEventManagerImplementsEventManager() {
        assertImplements(LEM, EventManager.class);
    }

    @Test
    public void testLocalEventManagerImplementsUpdateEventMethod() {
        assertImplementsMethod(LEM, new Class[]{Event.class}, "updateEvent");
    }

    @Test
    public void testLocalEventManagerImplementsIsEventInMethod() {
        assertImplementsMethod(LEM, new Class[]{String.class}, "isEventIn");
    }

    @Test
    public void testLocalEventManagerImplementsGetEventMethod() {
        assertImplementsMethod(LEM, new Class[]{String.class}, "getEvent");
    }

    @Test
    public void testLocalEventManagerImplementsGetAllEventMethod() {
        assertImplementsMethod(LEM, null, "getAllEvents");
    }
}
