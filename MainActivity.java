package com.lamnn.demoservice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnItemClickListener, View.OnClickListener{

    static final String EXTRA_PATH = "PATH";
    static final String EXTRA_TITLE = "TITLE";
    private static final int REQUEST_ID_READ_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if (isAllowPermission(Manifest.permission.READ_EXTERNAL_STORAGE)){
            showSongs(getSongs());
        }
    }

    private void initViews(){
        findViewById(R.id.button_play).setOnClickListener(this);
        findViewById(R.id.button_next).setOnClickListener(this);
    }

    private void showSongs(ArrayList<Song> songs){
        RecyclerView recyclerView = findViewById(R.id.recycle_list_songs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SongAdapter adapter = new SongAdapter(songs,this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Song> getSongs(){
        return new SongManager().getPlayList();
    }

    private boolean isAllowPermission(String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int permission = ActivityCompat.checkSelfPermission(this, permissionName);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permissionName}, REQUEST_ID_READ_PERMISSION);
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            showSongs(getSongs());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            
        }
    }

    @Override
    public void onItemClick(Song song) {
        startService(PlayMP3Service.getPlayIntent(getApplicationContext(), song));
     }
}
