package com.example.thealarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.google.common.base.Optional;

import org.threeten.bp.Duration;
import org.threeten.bp.ZonedDateTime;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

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

    //*******************************
    // BASIC ALARM hh:mm
    //*******************************

    public void createAlarm(Alarm theAlarm) {
        alarmDAO.insert(theAlarm);
        if (!theAlarm.isCron()) { // Basic alarm
            if (theAlarm.getRepeatTime() > 0) {
                setRepeatAlarm(theAlarm);
            } else {
                setOneTimeAlarm(theAlarm);
            }
        } else { // Advanced alarm
            setCRONAlarm(theAlarm);
        }
    }

    public void setOneTimeAlarm(Alarm theAlarm) {
        PendingIntent alarmIntent = new AlarmActions(context).makeAlarmIntent(theAlarm);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, theAlarm.getStartTime(), alarmIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, theAlarm.getStartTime(), alarmIntent);
        }
    }

    public void setRepeatAlarm(Alarm theAlarm) {
        PendingIntent alarmIntent = new AlarmActions(context).makeAlarmIntent(theAlarm);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, theAlarm.getStartTime(),
                theAlarm.getRepeatTime(), alarmIntent);
    }

    public void setCRONAlarm(Alarm theAlarm) {
        long timeToNext = getMilisTillNextExecution(theAlarm.getCronString());
        PendingIntent theIntent = new AlarmActions(context).makeCronAlarmIntent(theAlarm);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeToNext, theIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeToNext, theIntent);
        }
    }

    public void cancelAlarm(Alarm theAlarm) {
        PendingIntent pendingIntent = new AlarmActions(context).makeAlarmIntent(theAlarm);
        alarmManager.cancel(pendingIntent);
    }

    //*******************************
    // ADVANCED ALARM (CRON)
    //*******************************

    private ExecutionTime getExecutionTime(String cronString) {
        CronDefinition cronDefinition =
                CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        Cron cron = parser.parse(cronString);
        ExecutionTime executionTime = ExecutionTime.forCron(cron);
        return executionTime;
    }

    public long getMilisTillNextExecution(String cronString) {
        ZonedDateTime now = ZonedDateTime.now();
        Optional<Duration> timeToNextExecution = getExecutionTime(cronString).timeToNextExecution(now);
        return timeToNextExecution.get().getNano();
    }
}
