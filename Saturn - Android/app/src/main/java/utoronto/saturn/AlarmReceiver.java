package utoronto.saturn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Trigger the notification
        Log.d("Label", "Alarm Receive:");

        // Should be at some activity.
        NotificationScheduler.showNotification(context, MainActivity.class,
                "You have 5 subscribed unwatched videos", "Watch them now?");
    }
}

