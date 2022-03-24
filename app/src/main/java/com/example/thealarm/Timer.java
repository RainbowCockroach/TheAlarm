package com.example.thealarm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

// Cho tính năng hẹn giờ
@Entity(tableName = "TIMER")
public class Timer {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "time_pass_millisecond")
    private long timePassInMiliseconds; // Số giờ của hẹn giờ, tính bằng mili giây

    @Ignore
    public Timer(int hour, int minute, int second) {
        this.id = generateTimerId();
        this.timePassInMiliseconds = getTimeDistantInMilis(hour, minute, second);
    }

    public Timer(int id, long timePassInMiliseconds) {
        this.id = id;
        this.timePassInMiliseconds = timePassInMiliseconds;
    }

    private int generateTimerId() {
        return (int) System.currentTimeMillis();
    }

    public long getTimeDistantInMilis(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTimeInMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimePassInMiliseconds() {
        return timePassInMiliseconds;
    }

    public void setTimePassInMiliseconds(long timePassInMiliseconds) {
        this.timePassInMiliseconds = timePassInMiliseconds;
    }
}
