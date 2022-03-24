package com.example.thealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

public class TimerController {
    private Context context;
    private AlarmManager alarmManager;

    public TimerController(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    private PendingIntent makeTimerIntent(Timer theTimer) {
        Intent intent = new Intent(context, Test_AlarmGoOffActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, theTimer.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void setTimer(Timer theTimer) {
        PendingIntent alarmIntent = makeTimerIntent(theTimer);
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(SystemClock.elapsedRealtime() + theTimer.getTimePassInMiliseconds(), alarmIntent), alarmIntent);
    }

    public void cancelAlarm(Timer theTimer) {
        PendingIntent pendingIntent = makeTimerIntent(theTimer);
        alarmManager.cancel(pendingIntent);
    }
}
