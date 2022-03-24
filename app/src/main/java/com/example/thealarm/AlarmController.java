package com.example.thealarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import java.time.LocalDateTime;
import java.util.Calendar;

public class AlarmController {
    private static final String TAG = "alarmController";
    private Context context;
    private AlarmManager alarmManager;
    private AlarmDAO alarmDAO;

    public AlarmController(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        AppDatabase db = AppDatabase.getDatabase(context);
        this.alarmDAO = db.alarmDAO();
    }

    private PendingIntent makeAlarmIntent(Alarm theAlarm) {
        Intent intent = new Intent(context, Test_AlarmGoOffActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, theAlarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void createAlarm(Alarm theAlarm) {
        alarmDAO.insert(theAlarm);
        if (theAlarm.getRepeatTime() > 0) {
            setRepeatAlarm(theAlarm);
        } else {
            setOneTimeAlarm(theAlarm);
        }
    }

    public void setOneTimeAlarm(Alarm theAlarm) {
        PendingIntent alarmIntent = makeAlarmIntent(theAlarm);
        Log.d(TAG, "setOneTimeAlarm: start time: "+ theAlarm.getStartTime());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, theAlarm.getStartTime(), alarmIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, theAlarm.getStartTime(), alarmIntent);
        }
    }

    public void setRepeatAlarm(Alarm theAlarm) {
        PendingIntent alarmIntent = makeAlarmIntent(theAlarm);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, theAlarm.getStartTime(),
                theAlarm.getRepeatTime(), alarmIntent);
    }

    public void setAdvancedAlarm(Alarm theAlarm) {
        // TODO: set advanced alarm
    }

    public void cancelAlarm(Alarm theAlarm) {
        PendingIntent pendingIntent = makeAlarmIntent(theAlarm);
        alarmManager.cancel(pendingIntent);
    }
}
