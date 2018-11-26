package utoronto.saturn;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationScheduler {
    public static final int DAILY_REMINDER_REQUEST_CODE=100;
    public static final String TAG="NotificationScheduler";

    public static void setReminder(Context context, Class<?> generic_class, int hour, int min) {
        // Get the current time(hour, min, sec).
        Calendar calendar = Calendar.getInstance();

        // Set the given time(hour, min, sec)
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.set(Calendar.HOUR_OF_DAY, hour);
        setCalendar.set(Calendar.MINUTE, min);
        setCalendar.set(Calendar.SECOND, 0);

        // If the setting time is already before the current time, then cancel it.
        if(setCalendar.before(calendar))
            setCalendar.add(Calendar.DATE,1);

        Intent new_intent = new Intent(context, generic_class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, new_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        //set repeating every 24 hours
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public static void showNotification(Context context,Class<?> generic_class,String title,String content)
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, generic_class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(generic_class);
        stackBuilder.addNextIntent(notificationIntent);

        // PendingIntent fires when the alarm is triggered.
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(DAILY_REMINDER_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

        // Log out the title and content.
        Log.d("label", title);
        Log.d("label", content);


        // Set up the title, text, intent of the notification.
        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .addAction(android.R.drawable.sym_action_chat, "Chat", pendingIntent)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);

    }
}
