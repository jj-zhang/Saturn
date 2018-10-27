package utoronto.saturn.app.front_end.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import utoronto.saturn.app.R;


public class BaseView extends AppCompatActivity {
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
    }

    // Referenced from https://awsrh.blogspot.com/2017/10/custom-pop-up-window-with-android-studio.html
    public void ShowPopup(View v) {
        System.out.println("clicked pop up");
        TextView close_popup;
        Button register_event;
        myDialog.setContentView(R.layout.event_description_popup);
        close_popup =(TextView) myDialog.findViewById(R.id.close_event_popup);
        close_popup.setText("X");
        register_event = (Button) myDialog.findViewById(R.id.register_button);

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

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
