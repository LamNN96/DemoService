package com.lamnn.demoservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.lamnn.demoservice.App.CHANNEL_ID;

public class PlayMP3 extends Service {

    private MediaPlayer mediaPlayer;
    private String mPath;
    private String mTitle;

    public PlayMP3() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("framgia", intent.getExtras().getString("path"));
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mPath = intent.getExtras().getString("path");
        mTitle = intent.getExtras().getString("title");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(mPath));

        mediaPlayer.start();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, AUDIO_SERVICE)
                .setContentTitle("Demo MP3")
                .setContentText(mTitle)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
}
