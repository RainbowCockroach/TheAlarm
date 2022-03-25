package com.example.thealarm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Alarm... alarms);

    @Update
    void update (Alarm alarm);

    @Delete
    void delete (Alarm alarm);

    @Query("SELECT * FROM NORMAL_ALARM")
    List<Alarm> getListAlarm();
}
