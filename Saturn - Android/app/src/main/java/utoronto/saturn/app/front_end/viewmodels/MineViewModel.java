package utoronto.saturn.app.front_end.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import utoronto.saturn.Event;

public class MineViewModel extends ViewModel {
    private List<Event> mEvents;

    public MineViewModel(){
        // TODO: remove hardcoded data

        mEvents = new ArrayList<> ();
//        Event event = new Event("0", "Gorillaz The Now Now Tour", "Tour of Gorillaz",
//                1, 1, null, 1007);
//        mEvents.add(event);
    }

    public List<Event> getEvents() {
        return mEvents;
    }
}
