package com.example.thealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.google.common.base.Optional;

import org.threeten.bp.Duration;
import org.threeten.bp.ZonedDateTime;

public class AlarmActions {
    private Context context;

    public AlarmActions(Context context) {
        this.context = context;
    }

    public Bundle putAlarmInIntent(Alarm theAlarm) {
        Bundle theBundle = new Bundle();
        theBundle.putInt(Constants.KEY_ALARM_ID, theAlarm.getId());
        theBundle.putString(Constants.KEY_ALARM_NAME, theAlarm.getName());
        theBundle.putString(Constants.KEY_ALARM_CRON_STRING, theAlarm.getCronString());
        theBundle.putString(Constants.KEY_ALARM_SOUND, theAlarm.getSound());
        theBundle.putString(Constants.KEY_ALARM_GROUP_ID, theAlarm.getGroupId());
        theBundle.putBoolean(Constants.KEY_ALARM_HAS_CAPTCHA, theAlarm.getHasCaptcha());
        return theBundle;
    }

    public Alarm buildAlarmFromBundle(Bundle theBundle) {
        Alarm theAlarm = new Alarm();
        theAlarm.setId(theBundle.getInt(Constants.KEY_ALARM_ID, -1));
        theAlarm.setName(theBundle.getString(Constants.KEY_ALARM_NAME, Constants.DEFAULT_ALARM_NAME));
        theAlarm.setCronString(theBundle.getString(Constants.KEY_ALARM_CRON_STRING));
        theAlarm.setSound(theBundle.getString(Constants.KEY_ALARM_SOUND, Constants.DEFAULT_ALARM_SOUND));
        theAlarm.setGroupId(theBundle.getString(Constants.KEY_ALARM_GROUP_ID));
        theAlarm.setHasCaptcha(theBundle.getBoolean(Constants.KEY_ALARM_HAS_CAPTCHA, false));
        return theAlarm;
    }

    public PendingIntent makeAlarmIntent(Alarm theAlarm) {
        Intent intent = new Intent(context, AlarmGoOffActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent theIntent = PendingIntent.getActivity(context, theAlarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return theIntent;
    }

    public PendingIntent makeCronAlarmIntent(Alarm theAlarm) {
        // Make intent
        Intent intent = new Intent(context, AlarmGoOffActivity.class);
        intent.putExtra(Constants.KEY_ALARM_OBJECT, putAlarmInIntent(theAlarm));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent theIntent = PendingIntent.getActivity(context, theAlarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return theIntent;
    }
}
