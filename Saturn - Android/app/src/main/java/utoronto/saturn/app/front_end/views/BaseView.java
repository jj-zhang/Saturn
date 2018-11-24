package utoronto.saturn.app.front_end.views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;

import utoronto.saturn.Event;
import utoronto.saturn.User;
import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.adapters.EventItemFullAdapter;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class BaseView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Referenced from https://awsrh.blogspot.com/2017/10/custom-pop-up-window-with-android-studio.html

    public void eventPopUp(Event event) {
        Dialog myDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        System.out.println("clicked pop up");
        myDialog.setContentView(R.layout.event_description_popup);
        TextView close_popup = (TextView) myDialog.findViewById(R.id.close_event_popup);
        close_popup.setText("X");
        Button register_event = (Button) myDialog.findViewById(R.id.register_button);

        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closes the pop up if the user clicks the X
                myDialog.dismiss();
            }
        });

        register_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO
                // Add the user to the list of people registered for this event
            }
        });

        updateEventPopUpInfo(myDialog, event);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void artistPopUp(User user) {
        Dialog myDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        // v.getParent();
        System.out.println("clicked pop up");
        myDialog.setContentView(R.layout.artist_description_popup);
        TextView close_popup = (TextView) myDialog.findViewById(R.id.close_artist_popup);
        close_popup.setText("X");

        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closes the pop up if the user clicks the X
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    // Referenced from https://stackoverflow.com/questions/6407324/how-to-display-image-from-url-on-android
    // Used to draw an image onto the image view in the events popup
//    public void LoadImageFromWebOperations(URL url) {
//        try {
//            InputStream is = (InputStream) url.getContent();
//            Drawable image = Drawable.createFromStream(is, "src name");
//            ImageView imgview_event_image = myDialog.findViewById(R.id.event_image);
//            imgview_event_image.setImageDrawable(image);
//        } catch (Exception e) {
//            // TO DO: Need to print the exception where the URL is invalid
//            System.out.println("Invalid ");
//        }
//    }

    private void updateEventPopUpInfo(Dialog dialog, Event event) {
        // TODO: update text boxes with event info
        TextView textView_description = dialog.findViewById(R.id.event_description);
        textView_description.setText(event.getDescription());

        TextView textView_location = dialog.findViewById(R.id.event_location);
        textView_location.setText("Scotiabank Arena");

        TextView textView_date = dialog.findViewById(R.id.event_date);
        textView_date.setText(String.valueOf(event.getReleaseDate()));

        TextView textView_event = dialog.findViewById(R.id.event_artist);
        textView_event.setText("Drake");

        TextView textView_name = dialog.findViewById(R.id.event_name);
        textView_name.setText(event.getName());
    }
}
