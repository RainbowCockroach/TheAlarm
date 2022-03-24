package com.example.thealarm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Alarm.class, Timer.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlarmDAO alarmDAO();
    public abstract TimerDAO timerDAO();

    public static AppDatabase currentDatabase;
    public static AppDatabase getDatabase(Context context) {
        if (currentDatabase == null) {
            currentDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return currentDatabase;
    }
}
