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
    static final String EXTRA_PATH = "PATH";
    static final String EXTRA_TITLE = "TITLE";

    private ArrayList<HashMap<String, String>> mListSong;

    public SongAdapter(ArrayList<HashMap<String, String>> userList) {
        mListSong = userList;
    }


    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SongHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_song, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder,final int i) {
        songHolder.mItemName.setText(mListSong.get(i).get("songTitle"));
        songHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), mListSong.get(i).get("songPath") ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), PlayMP3.class);
                Bundle bundle = new Bundle();

                bundle.putString(EXTRA_PATH,mListSong.get(i).get("songPath"));
                bundle.putString(EXTRA_TITLE,mListSong.get(i).get("songTitle"));
                intent.putExtras(bundle);
                v.getContext().startService(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListSong.size();
    }
}
