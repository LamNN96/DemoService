package com.lamnn.demoservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SongAdapter extends RecyclerView.Adapter<SongHolder>{

    private ArrayList<HashMap<String, String>> songList;

    public SongAdapter(ArrayList<HashMap<String, String>> userList) {
        songList = userList;
    }


    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SongHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_song, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder,final int i) {
        songHolder.itemName.setText(songList.get(i).get("songTitle"));
        songHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), songList.get(i).get("songPath") ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), PlayMP3.class);
                Bundle bundle = new Bundle();

                bundle.putString("path",songList.get(i).get("songPath"));
                bundle.putString("title",songList.get(i).get("songTitle"));
                intent.putExtras(bundle);
                v.getContext().startService(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}
