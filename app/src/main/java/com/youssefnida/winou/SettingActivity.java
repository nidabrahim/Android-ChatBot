package com.youssefnida.winou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.youssefnida.winou.DB.CITATION_DBMANAGER;

import res.layout.AcceuilActivity;

public class SettingActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btn;
    private TextView time1;
    private TextView time2;
    private int hours;
    private int minute;
    private Activity currentActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        /**MENU**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentActivity = this;

        timePicker = (TimePicker) findViewById(R.id.Time);
        btn = (Button) findViewById(R.id.btn_ok);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);

        CITATION_DBMANAGER mngg = new CITATION_DBMANAGER(this);
        int h = mngg.getHeure1();int h12 = 0;
        int m = mngg.getMinute1();
        time1.setText(h+":"+m);

        if(h>=0 && h<12) h12 = h+12;
        if(h>=12 && h<=23) h12 = h-12;

        time2.setText(h12+":"+m);

        /** VALIDATION DE L'ALARME **/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hours = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();

                Toast.makeText(v.getContext(), hours + ":"+ minute , Toast.LENGTH_LONG).show();

                /** SAVE TIME ALARM **/
                CITATION_DBMANAGER mng = new CITATION_DBMANAGER(v.getContext());
                mng.insertCitationAlarm(hours,minute);

                /** BACK **/
                Intent intent = new Intent(currentActivity,AcceuilActivity.class);
                currentActivity.startActivity(intent);

            }
        });

    }
}
