package com.lamnn.demoservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class SongHolder extends RecyclerView.ViewHolder  {

    protected TextView itemName;

    public SongHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.text_item_name);
    }



}
