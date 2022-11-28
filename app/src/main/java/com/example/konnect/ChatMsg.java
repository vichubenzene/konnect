package com.example.konnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatMsg extends AppCompatActivity {

    FirebaseDatabase database;
    TextView txttitle;
    FirebaseAuth auth;
    ImageView sendimg;
    EditText sendtxt;
    RecyclerView chat_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_msg);
        sendimg=findViewById(R.id.send_bton);
        sendtxt = findViewById(R.id.type_et);
        chat_rv = findViewById(R.id.rv_chat);
        database = FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        final String senderId=auth.getUid();
        String receiverId = getIntent().getStringExtra("user_id");
        String username = getIntent().getStringExtra("username");
        txttitle = findViewById(R.id.name_chat);
        txttitle.setText(username);
        final ArrayList<MessageModel> messageModels = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(messageModels,this);
        chat_rv.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chat_rv.setLayoutManager(layoutManager);
        final String senderRoom = senderId+receiverId;
        final String receiverRoom = receiverId+senderId;
        database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    MessageModel model = snapshot1.getValue(MessageModel.class);
                    messageModels.add(model);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sendimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = sendtxt.getText().toString();
                final MessageModel Model = new MessageModel(senderId,message);
                Model.setTimestamp(new Date().getTime());
                sendtxt.setText("");
                database.getReference().child("chats").child(senderRoom).push().setValue(Model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.getReference().child("chats").child(receiverRoom).push().setValue(Model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                    }
                });

            }
        });

    }
}