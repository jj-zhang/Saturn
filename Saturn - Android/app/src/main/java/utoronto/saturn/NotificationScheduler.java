package utoronto.saturn;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationScheduler {
    public static final int DAILY_REMINDER_REQUEST_CODE=100;
    public static final String TAG="NotificationScheduler";

    public static void setReminder(Context context, Class<?> cls, int hour, int min) {
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

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        // set repeating every 24 hours
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public static void showNotification(Context context,Class<?> cls,String title,String content)
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        // PendingIntent fires when the alarm is triggered.
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(DAILY_REMINDER_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, notification);

    }
}
