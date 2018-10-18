package utoronto.saturn;

import java.net.URL;

class Event {
    private String ID;
    private String name;
    private String desc;
    private double rating;
    private double userRating;
    private URL imageURL;

    Event(String ID, String name, String desc, double rating, double userRating, URL imageURL) {
        this.ID = ID;
        this.name = name;
        this.desc = desc;
        this.rating = rating;
        this.userRating = userRating;
        this.imageURL = imageURL;
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
        return imageURL;
    }
}
