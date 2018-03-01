package com.youssefnida.winou;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by youssefNIDA on 25/04/2017.
 */

public class NotificationReciever  extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,QuotesActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context).
                setSmallIcon(R.drawable.quotes).
                setContentIntent(pendingIntent).
                setContentText("Cliquer pour voir une nouvelle citation").
                setContentTitle("Citation du jour").
                setDefaults(NotificationCompat.DEFAULT_SOUND).
        setAutoCancel(true);
        notificationManager.notify(100,builder.build());

    }
}
