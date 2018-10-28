package com.lamnn.demoservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import static com.lamnn.demoservice.MainActivity.EXTRA_PATH;
import static com.lamnn.demoservice.MainActivity.EXTRA_TITLE;


public class PlayMP3Service extends Service {

    private MediaPlayer mMediaPlayer;
    private String mPath;
    private String mTitle;

    public PlayMP3Service() {

    }

    public static Intent getPlayIntent(Context context, Song song){
        Intent intent = new Intent(context, PlayMP3Service.class);
        intent.putExtra(EXTRA_PATH, song.getPath());
        intent.putExtra(EXTRA_TITLE, song.getTitle());
        return intent;
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
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        mPath = intent.getExtras().getString(EXTRA_PATH);
        mTitle = intent.getExtras().getString(EXTRA_TITLE);
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(mPath));

        mMediaPlayer.start();

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
        mMediaPlayer.release();
        super.onDestroy();
    }

}
