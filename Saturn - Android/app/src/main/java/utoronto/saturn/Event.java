package utoronto.saturn;

import java.net.URL;

public class Event {
    private String ID;
    private String name;
    private String desc;
    private double rating;
    private double userRating;
    private URL imageURL;
    private long releaseDate;

    Event(String ID, String name, String desc, double rating, double userRating, URL imageURL, long releaseDate) {
        if(ID.replaceAll(" ", "").length() == 0) {
            throw new IllegalArgumentException("ID is empty!");
        } else if(name.replaceAll(" ", "").length() == 0) {
            throw new IllegalArgumentException("Name is empty!");
        } else if(rating < -1) { // -1 == unrated
            throw new IllegalArgumentException("Rating is a negative number!");
        } else if(userRating < -1) {
            throw new IllegalArgumentException("UserRating is a negative number!");
        } else if(releaseDate < -1) {
            throw new IllegalArgumentException("Release date is a negative number!");
        }

        this.ID = ID;
        this.name = name;
        this.desc = desc;
        this.rating = rating;
        this.userRating = userRating;
        this.imageURL = imageURL;
        this.releaseDate = releaseDate;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public double getRating() {
        return rating;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double newRating) {
        this.userRating = newRating;
        // Call to database to update this rating
    }

    public URL getImageURL() {
        return this.imageURL;
    }

    public long getReleaseDate() {
        return this.releaseDate;
    }

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
