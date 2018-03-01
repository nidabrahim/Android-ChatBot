package com.youssefnida.winou;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import res.layout.AcceuilActivity;

public class MainActivity extends AppCompatActivity {

    Activity currentActivity;
    //private CITATION_DBMANAGER citationDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** PERMISSIONS **/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        currentActivity = this;

        /** TITLE **/
        TextView winou = (TextView) findViewById(R.id.winou);
        TextView desc = (TextView) findViewById(R.id.desc);

        /** FONT FAMILY **/
        setFont(winou,"Linlegreyregular.otf");
       /* setFont(desc,"March into Spring - OTF.otf");*/

        /** ALARM NOTIFICATION **/
       /* citationDb = new CITATION_DBMANAGER(this);
        int heureAlarm = citationDb.getHeure1();
        int minAlarm = citationDb.getMinute1();
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());
        int curHr = calender.get(Calendar.HOUR_OF_DAY);
        int cur = calender.get(Calendar.MINUTE);
        if (curHr >= heureAlarm+1 )
        {
            calender.add(Calendar.DATE, 1);
        }

        calender.set(Calendar.HOUR_OF_DAY, heureAlarm);
        calender.set(Calendar.MINUTE, minAlarm);
        calender.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if (savedInstanceState == null) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(),AlarmManager.INTERVAL_HALF_DAY , pendingIntent);
        }*/
        /************************/

        /** LOAD QUOTES FILES **/
        loadQuotes();

        /** TIME WAITING **/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(currentActivity,AcceuilActivity.class);
                currentActivity.startActivity(intent);
            }

        }).start();

        /*MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.ring);
        ring.start();*/


    }


    /** SET FONT FAMILY **/
    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets() , "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }


    /** QUOTES FILES **/
    private void loadQuotes(){

        File fileExt = new File(Environment.getExternalStorageDirectory().toString()+"/bots");

        if(!fileExt.exists())
        {
            try
            {
                File jayDir = new File(Environment.getExternalStorageDirectory().toString()+"/bots");
                jayDir.mkdirs();

                File jayDirQuotes = new File(Environment.getExternalStorageDirectory().toString()+"/bots/quotes");
                jayDirQuotes.mkdirs();

                File jayDirchild1 = new File(Environment.getExternalStorageDirectory().toString()+"/bots/quotes/aimlif");
                jayDirchild1.mkdirs();

                File jayDirchild2 = new File(Environment.getExternalStorageDirectory().toString()+"/bots/quotes/aiml");
                jayDirchild2.mkdirs();

                InputStream in = getAssets().open("quotes/aiml/quotes.aiml");
                OutputStream out = new FileOutputStream(jayDirchild2.getPath() + "/quotes.aiml");

                copyFile(in, out);

                } catch (Exception e) { e.printStackTrace(); }
        }

    }


    /** COPING FILE **/
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

}
