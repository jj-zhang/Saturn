package utoronto.saturn;

import java.util.List;

interface EventManager {
    /*
        Requests an updated version of this Event from database
     */
    void updateEvent(Event event);

    /*
        Returns whether or not eventList contains the key <ID>
     */
    boolean isEventIn(String ID);

    /*
        Gets event that matches id from list or database
     */
    Event getEvent(String ID);

    /*
    Returns List of all Local Events
     */
    public List<Event> getAllEvents();
}
