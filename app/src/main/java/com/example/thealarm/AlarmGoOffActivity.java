package com.example.thealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class AlarmGoOffActivity extends AppCompatActivity {

    private static final String TAG = "alarmOffActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ALARM WENT OFF ");
        // TODO: Hàm đổ chuông các thứ sẽ ở đây
        // For advanced  alarm: to next alarm
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String cronString = extras.getString("cron");
            int alarmId = Integer.parseInt(extras.getString("alarmId"));
            Log.d(TAG, "onCreate: cron string: "+ cronString);
            if (cronString != null) {
                long nextExecuteTime = new AlarmController(this).milisTillNextExecution(cronString);
                Alarm temp = new Alarm();
                temp.setId(alarmId);
                temp.setCronString(cronString);
                new AlarmController(this).setOneTimeAlarm(temp);
            }
        }
    }
}