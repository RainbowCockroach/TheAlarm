package com.example.thealarm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
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
    @ColumnInfo(name = "is_repeat")
    private boolean isRepeat; // = true: báo thức lặp lại, không thì là báo thức 1 lần
    @ColumnInfo(name = "start_time")
    private LocalDateTime startTime;
    @ColumnInfo(name = "cron_time")
    private String timeString; // Kiểu cron
    @ColumnInfo(name = "sound")
    private String sound; // File âm thanh khi báo thức kêu
    @ColumnInfo(name = "group_id")
    // Có thể có hoặc không - tính năng làm thêm
    private String groupId;

    public Alarm(String name, boolean isOn, boolean isRepeat, LocalDateTime startTime, String timeString) {
        this.id = (int) System.currentTimeMillis();
        this.name = name;
        this.isOn = isOn;
        this.isRepeat = isRepeat;
        this.startTime = startTime;
        this.timeString = timeString;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getTimeString() {
        return timeString;
    }

    public String getSound() {
        return sound;
    }

    public String getGroupId() {
        return groupId;
    }
}
