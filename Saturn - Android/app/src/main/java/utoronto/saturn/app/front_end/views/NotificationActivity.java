package utoronto.saturn.app.front_end.views;

import android.app.Activity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;

public class NotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNotification(View view) {
        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);

        // Set up the title, text, intent of the notification.
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Lunch is ready!")
                .setContentText("It's getting cold...")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .addAction(android.R.drawable.sym_action_chat, "Chat", pendingIntent)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }
}
