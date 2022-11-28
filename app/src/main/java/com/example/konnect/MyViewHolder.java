package com.example.konnect;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView userName,last;
    ImageView profile;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.nameuser);
        last = itemView.findViewById(R.id.lastmsg);
        profile = itemView.findViewById(R.id.profilepic);
    }
}
