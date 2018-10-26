package com.lamnn.demoservice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button mButtonStart, mButtonNext, mButtonPrev;
    private RecyclerView mRecyclerViewListSong;
    private RecyclerView.Adapter mSongAdapter;
    private RecyclerView.LayoutManager mSongLayoutManager;
    private SongManager mSongManager;
    public ArrayList<HashMap<String, String>> listSong;

    private static final int REQUEST_ID_READ_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonStart = findViewById(R.id.button_play);
        mButtonNext = findViewById(R.id.button_next);
        mButtonPrev = findViewById(R.id.button_prev);

        mRecyclerViewListSong = findViewById(R.id.recycle_list_songs);

        mSongLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewListSong.setLayoutManager(mSongLayoutManager);

        mSongManager = new SongManager();
        listSong = mSongManager.getPlayList();
        mSongAdapter = new SongAdapter(listSong);

        mRecyclerViewListSong.setAdapter(mSongAdapter);


        askPermission(REQUEST_ID_READ_PERMISSION, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private boolean askPermission(int requestId, String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            int permission = ActivityCompat.checkSelfPermission(this, permissionName);


            if (permission != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }




}
