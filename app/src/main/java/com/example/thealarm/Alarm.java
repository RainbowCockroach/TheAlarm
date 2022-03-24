package com.example.thealarm;

import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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

import java.util.Calendar;
import java.util.Locale;

// Cho tính năng báo thức bình thường
@Entity(tableName = "NORMAL_ALARM")
public class Alarm {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "is_on")
    private boolean isOn;
    @ColumnInfo(name = "is_cron")
    private boolean isCron;

    // BÁO THỨC CƠ BẢN
    @ColumnInfo(name = "start_time_milis")
    private long startTime;
    @ColumnInfo(name = "repeat_time_milis")
    private long repeatTime;

    // CRON
    @ColumnInfo(name = "cron_time")
    private String cronString;

    // CÀI ĐẶT
    @ColumnInfo(name = "sound")
    private String sound;
    @ColumnInfo(name = "group_id")
    private String groupId;
    @ColumnInfo(name = "has_captcha")
    private Boolean hasCaptcha;

    @Ignore
    public Alarm() {
        this.id = generateAlarmId();
        this.name = Constants.DEFAULT_ALARM_NAME;
        this.isOn = true;
        this.isCron = false;
        this.startTime = System.currentTimeMillis();
        this.repeatTime = 1000 * 60*60*12;
        this.cronString = "";
        this.sound = "";
        this.groupId = "";
        this.hasCaptcha = false;
    }


    @Ignore
    public Alarm(int hour, int minutes, String name) {
        this.id = generateAlarmId();
        this.name = name;
        this.isOn = true;
        this.startTime = getAlarmTimeInMilis(hour, minutes);
    }

    @Ignore
    public Alarm(int hour, int minutes, String name, long repeatTime) {
        this.id = generateAlarmId();
        this.name = name;
        this.isOn = true;
        this.startTime = getAlarmTimeInMilis(hour, minutes);
        this.repeatTime = repeatTime;
    }

    public Alarm(int id, String name, boolean isOn, boolean isCron, long startTime, long repeatTime, String cronString, String sound, String groupId, Boolean hasCaptcha) {
        this.id = id;
        this.name = name;
        this.isOn = isOn;
        this.isCron = isCron;
        this.startTime = startTime;
        this.repeatTime = repeatTime;
        this.cronString = cronString;
        this.sound = sound;
        this.groupId = groupId;
        this.hasCaptcha = hasCaptcha;
    }

    private int generateAlarmId() {
        return (int) System.currentTimeMillis();
    }

    public long getAlarmTimeInMilis(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }

    public long getRepeatTimeInMilis(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis() - System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isCron() {
        return isCron;
    }

    public void setCron(boolean cron) {
        isCron = cron;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(long repeatTime) {
        this.repeatTime = repeatTime;
    }

    public String getCronString() {
        return cronString;
    }

    public void setCronString(String cronString) {
        this.cronString = cronString;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Boolean getHasCaptcha() {
        return hasCaptcha;
    }

    public void setHasCaptcha(Boolean hasCaptcha) {
        this.hasCaptcha = hasCaptcha;
    }

    public String getCronDescription() {
        CronDefinition cronDefinition =
                CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        CronDescriptor descriptor = CronDescriptor.instance(Locale.ENGLISH);
        String description = descriptor.describe(parser.parse(cronString));
        return description;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "name='" + name + '\'' +
                '}';
    }
}
