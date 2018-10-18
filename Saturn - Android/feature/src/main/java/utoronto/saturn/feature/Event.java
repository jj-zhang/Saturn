package utoronto.saturn.feature;

import java.util.List;

interface Event {
    void updateEvent(Event event);

    Event getEvent(String id);

    List<Event> getAllEvents();

    boolean addEvent(String id);

    boolean removeEvent(String id);
}
