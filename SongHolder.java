package com.lamnn.demoservice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SongHolder extends RecyclerView.ViewHolder  {

    protected TextView mItemName;

    public SongHolder(@NonNull View itemView) {
        super(itemView);
        mItemName = itemView.findViewById(R.id.text_item_name);
    }



}
