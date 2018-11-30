package utoronto.saturn.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("label","Alarm Receive:");
        Intent intent1 = new Intent(context, MyNewIntentService.class);
        context.startService(intent1);
    }
}