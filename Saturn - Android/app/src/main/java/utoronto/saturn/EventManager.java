package utoronto.saturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class EventManager {
    public Map<String, Event> eventList;
    /*
        Requests an updated version of this Event from database
     */
    public void updateEvent(Event event) {
        if(isEventIn(event.getID())) {
            eventList.remove(event.getID());
        }

        // get info from database
    }

    /*
        Returns whether or not eventList contains the key <ID>
     */
    public boolean isEventIn(String ID) {
        return eventList.containsKey(ID);
    }

    /*
        Gets event that matches id from list or database
     */
    public Event getEvent(String ID) {
        return eventList.get(ID);
    }

    /*
        Returns List of all Local Events
     */
    public List<Event> getAllEvents() {
        return new ArrayList<Event>(eventList.values());
    }

    /*
        Adds event to list of LocalEvents using its <ID>
     */
    public boolean addEvent(String ID, Event event) {
        // UserRating is 0 by default.
        if(isEventIn(ID)) {
            return false;
        } else {
            eventList.put(ID, event);
            return true;
        }
    }

    /*
        Removes event from eventList using its <ID>
     */
    public boolean removeEvent(String ID) {
        if(isEventIn(ID)) {
            eventList.remove(ID);
            return true;
        } else {
            return false;
        }
    }
}
