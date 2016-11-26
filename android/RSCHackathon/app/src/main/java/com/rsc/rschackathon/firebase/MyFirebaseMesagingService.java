package com.rsc.rschackathon.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.activities.MainActivity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

public class MyFirebaseMesagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMesagingService.class.getSimpleName();

    public static final String TEXT_EXTRA = "TEXT";

    public static final String TITLE_EXTRA = "TITLE";

    Random rand = new Random();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "FROM:" + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data: " + remoteMessage.getData());
            sendNotification(remoteMessage.getData().get("text"), remoteMessage.getData().get("title"));
        }
    }

    /**
     * Dispay the notification
     */
    private void sendNotification(String body, String title) {
        int randomNum = rand.nextInt((9999999 - 1) + 1) + 1;

        Intent intent = new Intent(this, MainActivity.class).putExtra(TEXT_EXTRA, body).putExtra(TITLE_EXTRA, title);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, randomNum, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(randomNum, notifiBuilder.build());
    }
}
