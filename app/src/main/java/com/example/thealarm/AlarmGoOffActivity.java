package com.example.thealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AlarmGoOffActivity extends AppCompatActivity {
    private static final String TAG = "alarmOffActivity";
    private Alarm currentTempAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_go_off);
        // Read params
        Bundle extras = getIntent().getExtras();
        Bundle alarmInfo = extras.getBundle(Constants.KEY_ALARM_OBJECT);
        Log.d(TAG, "onCreate: extras: ;" + extras);
        Log.d(TAG, "onCreate: extras: ;" + alarmInfo);
        if (extras != null && alarmInfo != null) {
            currentTempAlarm = new AlarmActions(this).buildAlarmFromBundle(alarmInfo);
            continueCronAlarm();
            doAlarmAction();
        }
    }

    private void turnOffAlarm(View view) {
        pressHomeButton();
    }

    /**
     * Hàm thực hiện các thứ khi báo thức kêu (đổ chuông, captcha...)
     */
    public void doAlarmAction() {
        ((TextView) findViewById(R.id.txt_alarmName_alarmGoOffActivity)).setText(currentTempAlarm.getName());
    }

    /**
     * Tắt báo thức, tương tự như bấm nút home
     */
    private void pressHomeButton() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void continueCronAlarm() {
        if (currentTempAlarm.getCronString() != null && currentTempAlarm.getId() != -1) {
            new AlarmController(this).setCRONAlarm(currentTempAlarm);
        }
    }
}