package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import utoronto.saturn.Event;

public class HomeViewModel extends ViewModel {
    private List<Event> myEvents;
    private List<Event> popularEvents;
    private List<Event> suggestedEvents;

    public HomeViewModel(){


        myEvents = new ArrayList<>();
        popularEvents = new ArrayList<>();
        suggestedEvents = new ArrayList<>();
//        Event event = new Event("0", "Gorillaz The Now Now Tour", "Tour of Gorillaz",
//                1, 1, null, 1007);
//
//        myEvents.add(event);
//        popularEvents.add(event);
//        suggestedEvents.add(new Event("1", "Boku No Hero Academia", "Shonen anime",
//                5, 5, null, 1105));
//        suggestedEvents.add(new Event("3", "Super Smash Bros Ultimate", "Nintendo game",
//                5, 5, null, 1130));
//        suggestedEvents.add(new Event("2", "The Nutcracker", "Disney movie",
//                5, 5, null, 1225));
    }

    public List<Event> getMyEvents() {
        return myEvents;
    }

    public List<Event> getPopularEvents() {
        return popularEvents;
    }

    public List<Event> getSuggestedEvents() {
        return suggestedEvents;
    }
}
