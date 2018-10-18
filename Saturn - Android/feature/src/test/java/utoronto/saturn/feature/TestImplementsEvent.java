package utoronto.saturn.feature;

import org.junit.Assert;
import org.junit.Test;

public class TestImplementsEvent {
    private static final Event LE = new LocalEvent();

    private void assertImplementsEvent(Object obj) {
        assert(obj instanceof Event) : "Class does not implement Event!";
    }

    private void assertImplementsEventMethods(Object obj, Class[] cArgs, String method) {
        try {
            obj.getClass().getMethod(method, cArgs);
        } catch(NoSuchMethodException e) {
            Assert.fail(obj.getClass() + " does not implement " + method + "!");
        }
    }

    @Test
    public void checkLocalEventImplementsEvent() {
        assertImplementsEvent(LE);
    }

    @Test
    public void checkLocalEventImplementsGetEventMethod() {
        assertImplementsEventMethods(LE, new Class[]{String.class}, "getEvent");
    }

    @Test
    public void checkLocalEventImplementsUpdateEventMethod() {
        assertImplementsEventMethods(LE, new Class[]{Event.class}, "updateEvent");
    }

    @Test
    public void checkLocalEventImplementsGetAllEventMethod() {
        assertImplementsEventMethods(LE, null, "getAllEvents");
    }

    @Test
    public void checkLocalEventImplementsAddEventMethod() {
        assertImplementsEventMethods(LE, new Class[]{String.class}, "addEvent");
    }

    @Test
    public void checkLocalEventImplementsRemoveEventMethod() {
        assertImplementsEventMethods(LE, new Class[]{String.class}, "removeEvent");
    }
}
