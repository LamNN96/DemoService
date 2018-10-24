package com.lamnn.demoservice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart, buttonNext, buttonPrev;
    private RecyclerView recyclerViewListSong;
    private RecyclerView.Adapter songAdapter;
    private RecyclerView.LayoutManager songLayoutManager;
    private SongManager songManager;
    public ArrayList<HashMap<String, String>> listSong;

    private static final int REQUEST_ID_READ_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button_play);
        buttonNext = findViewById(R.id.button_next);
        buttonPrev = findViewById(R.id.button_prev);

        recyclerViewListSong = findViewById(R.id.list_songs);

        songLayoutManager = new LinearLayoutManager(this);
        recyclerViewListSong.setLayoutManager(songLayoutManager);

        songManager = new SongManager();
        listSong = songManager.getPlayList();
        songAdapter = new SongAdapter(listSong);

        recyclerViewListSong.setAdapter(songAdapter);


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
