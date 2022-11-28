package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2500;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mAuth=FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAuth.getCurrentUser()!=null){
                    Intent intent = new Intent(splashscreen.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(splashscreen.this,userAuth.class);
                    startActivity(intent);
                }
                finish();
            }
        },SPLASH_SCREEN);
    }
}