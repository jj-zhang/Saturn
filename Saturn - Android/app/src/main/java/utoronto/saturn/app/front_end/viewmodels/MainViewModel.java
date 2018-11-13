package utoronto.saturn.app.front_end.viewmodels;


import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import java.net.URL;

import utoronto.saturn.Event;

public class MainViewModel extends ViewModel {
    URL jordanURL = null;
    Event jordans;
    String desc = "hello world";
        public MainViewModel() {
        try {

            jordanURL = new URL("https://images.glaciermedia.ca/polopoly_fs/1.23435369.1537333511!/fileImage/httpImage/image.jpg_gen/derivatives/landscape_804/jenna-morland-empress-book-jpg.jpg");
            jordans = new Event("Jays", "Jordans",  desc, 10.0, 9.0, jordanURL, 100);

        } catch (Exception exception) {

        }
    }


    public URL getImage(String id) {
        if (id.equals("Jays")) {
            return jordans.getImageURL();
        }
        return null;
    }

    public String getDescription(String id) {
        if (id.equals("Jays")) {
            return jordans.getDescription();
        }
        return null;
    }

    public long getReleaseDate(String id) {
        if (id.equals("Jays")) {
            return jordans.getReleaseDate();
        }
        return 0;
    }

    public double getUserRating(String id) {
        return 0;
    }

    public double getRating(String id) {
        return 0;
    }
}
