package utoronto.saturn.feature;

import java.util.Map;
import java.util.List;

public class LocalEvent implements Event {
    Map<String, Event> eventList;

    public void updateEvent(Event event) {
    }

    public Event getEvent(String id) {
        return null;
    }

    public List<Event> getAllEvents() {
        return null;
    }

    public boolean addEvent(String id) {
        return false;
    }

    public boolean removeEvent(String id) {
        return false;
    }
}
