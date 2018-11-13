package utoronto.saturn;

import java.net.URL;

public class Event {
    private String ID;
    private String name;
    private URL imageURL;
    private long releaseDate;


    Event(String ID, String name, URL imageURL, long releaseDate) {

        if(ID.replaceAll(" ", "").length() == 0) {
            throw new IllegalArgumentException("ID is empty!");
        } else if(name.replaceAll(" ", "").length() == 0) {
            throw new IllegalArgumentException("Name is empty!");
        } else if(releaseDate < -1) {
            throw new IllegalArgumentException("Release date is a negative number!");
        }

        this.ID = ID;
        this.name = name;
        this.imageURL = imageURL;
        this.releaseDate = releaseDate;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public URL getImageURL() {
        return this.imageURL;
    }

    public long getReleaseDate() {
        return this.releaseDate;
    }

    public String getDescription(){ return "Desc";}

    /*
        Checks if event is released
     */
    private boolean isReleased() {
        return this.releaseDate < System.currentTimeMillis();
    }

    /*
        Time till release date in system time
     */
    public long timeTillRelease() {
        if(isReleased()) {
            return 0;
        } else {
            // Add a func to turn this into readable time at some point...
            return this.releaseDate - System.currentTimeMillis();
        }
    }
}
