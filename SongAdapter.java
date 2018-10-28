package com.lamnn.demoservice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private ArrayList<Song> mSongs;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;

    SongAdapter(ArrayList<Song> songs, OnItemClickListener listener) {
        mSongs = songs;
        mListener = listener;
    }


    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null){
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_song, viewGroup, false);
        return  new SongViewHolder(view, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder songViewHolder, int i) {
        songViewHolder.bindView(mSongs.get(i));
    }



    @Override
    public int getItemCount() {
        return mSongs != null ? mSongs.size() : 0;
    }

    public interface OnItemClickListener {
        void onItemClick(Song song);
    }

    static class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener mOnItemClickListener;
        private TextView mTextName;
        private Song mSong;

        SongViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mOnItemClickListener = listener;
            mTextName = itemView.findViewById(R.id.text_item_name);
            itemView.setOnClickListener(this);
        }

        void bindView(Song song) {
            if (song != null)  mSong = song;
            else return;
            mTextName.setText(song.getTitle());
        }


        @Override
        public void onClick(View view) {
            if (mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onItemClick(mSong);
        }
    }

}
