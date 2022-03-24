package com.example.thealarm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

// Cho tính năng báo thức bình thường
@Entity(tableName = "NORMAL_ALARM")
public class Alarm {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "is_on")
    private boolean isOn; // Báo thức có đang bật hay đang tắt
    // Báo thức cơ bản
    @ColumnInfo(name = "start_time_milis")
    private long startTime;
    @ColumnInfo(name = "repeat_time_milis")
    private long repeatTime;
    // CRON
    @ColumnInfo(name = "cron_time")
    private String timeString; // Kiểu cron
    // OPTIONS
    @ColumnInfo(name = "sound")
    private String sound; // File âm thanh khi báo thức kêu
    @ColumnInfo(name = "group_id")
    private String groupId;

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

    public Alarm(int id, String name, boolean isOn, long startTime, long repeatTime, String timeString, String sound, String groupId) {
        this.id = id;
        this.name = name;
        this.isOn = isOn;
        this.startTime = startTime;
        this.repeatTime = repeatTime;
        this.timeString = timeString;
        this.sound = sound;
        this.groupId = groupId;
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

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
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

    @Override
    public String toString() {
        return "Alarm{" +
                "name='" + name + '\'' +
                '}';
    }
}
