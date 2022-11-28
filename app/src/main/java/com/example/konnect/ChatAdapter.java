package com.example.konnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{
    ArrayList<MessageModel> messageModels;
    Context c;
    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context c) {
        this.messageModels = messageModels;
        this.c = c;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(c).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(c).inflate(R.layout.sample_receiver,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModels.get(position).getuID().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).sendermsg.setText(messageModels.get(position).getMessage());
        }
        else{
            ((ReceiverViewHolder)holder).receivemsg.setText(messageModels.get(position).getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView receivemsg,receivetime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receivemsg=itemView.findViewById(R.id.tv_receivechat);
            receivetime=itemView.findViewById(R.id.tv_receivetime);
        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView sendermsg,sendertime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sendermsg=itemView.findViewById(R.id.tv_senderchat);
            sendertime=itemView.findViewById(R.id.tv_sendertime);
        }
    }
}
