package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import utoronto.saturn.Event;

public class CategoryViewModel extends ViewModel {
    private List<Event> animeEvents, movieEvents, gameEvents, concertEvents;

    public CategoryViewModel() {
        // TODO: change this to not use hardcoded info
//
//        animeEvents = new ArrayList<>();
//        animeEvents.add(new Event("1", "Boku No Hero Academia", "Shonen anime",
//                5, 5, null, 1105));
//
//        movieEvents = new ArrayList<>();
//        movieEvents.add(new Event("2", "The Nutcracker", "Disney movie",
//                5, 5, null, 1225));
//        gameEvents = new ArrayList<>();
//        gameEvents.add(new Event("3", "Super Smash Bros Ultimate", "Nintendo game",
//                5, 5, null, 1130));
//        concertEvents = new ArrayList<>();
//        concertEvents.add(new Event("0", "Gorillaz The Now Now Tour", "Tour of Gorillaz",
//                5, 5, null, 1000));
    }

    public List<Event> getEventsByCategory(String category) {
        switch (category) {
            case "Anime":
                return animeEvents;
            case "Movies":
                return movieEvents;
            case "Games":
                return gameEvents;
            case "Concerts":
                return concertEvents;
        }
        return null;
    }
}
