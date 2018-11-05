package utoronto.saturn;

import java.util.List;
public class GlobalEventManager extends EventManager {
    public GlobalEventManager() {
        super();
    }

    public List<Event> getPopular(){
        //from database
        return null;
    }
    public List<Event> getTrending(){
        //from database
        return null;
    }
    public List<Event> getSuggested(){
        return null;
    }
}