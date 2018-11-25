package utoronto.saturn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {

        String TAG = "AlarmReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && context != null) {
                if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                    // Set the alarm here.
                    Log.d(TAG, "onReceive: BOOT_COMPLETED");
                    int hour = 8;
                    int min = 30;
                    NotificationScheduler.setReminder(context, AlarmReceiver.class, hour, min);
                    return;
                }
            }

            Log.d(TAG, "onReceive: ");

            //Trigger the notification
            NotificationScheduler.showNotification(context, MainActivity.class,
                    "You have 5 new subscribed videos", "Watch them now?");

        }
}

