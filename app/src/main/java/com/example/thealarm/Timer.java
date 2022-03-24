package com.example.thealarm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Cho tính năng hẹn giờ
@Entity(tableName = "TIMER")
public class Timer {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "time_pass_millisecond")
    private long timePassInMiliseconds; // Số giờ của hẹn giờ, tính bằng mili giây

    public Timer(long timePassInMiliseconds) {
        this.id = (int) System.currentTimeMillis();
        this.timePassInMiliseconds = timePassInMiliseconds;
    }

    public int getId() {
        return id;
    }

    public long getTimePassInMiliseconds() {
        return timePassInMiliseconds;
    }
}
