package com.example.thealarm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thealarm.Timer;

import java.util.List;

@Dao
public interface TimerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Timer... timers);

    @Update
    void update (Timer timer);

    @Delete
    void delete (Timer timer);

    @Query("SELECT * FROM TIMER")
    List<Timer> getListTimer();
}
