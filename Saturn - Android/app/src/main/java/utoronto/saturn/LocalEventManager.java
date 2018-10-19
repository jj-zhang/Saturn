package utoronto.saturn;

public class LocalEventManager extends EventManager {

    public LocalEventManager() {
        super();
    }

    /*
        Turns a local event into a global one
     */
    public void convertToGlobal(String ID) {
        Event eventToAdd = getEvent(ID);

        // Add to database
    }
}
