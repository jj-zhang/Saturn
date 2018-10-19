package utoronto.saturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LocalEventManager extends EventManager {
    public LocalEventManager() {
        super.eventList = new HashMap<String, Event>();
    }

}
