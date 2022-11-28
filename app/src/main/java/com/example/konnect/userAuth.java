package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class userAuth extends AppCompatActivity {

    TextView signuptxt,signintxt;
    Button signinBton;
    EditText email, pass;
    FirebaseAuth auth;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);
        signuptxt = findViewById(R.id.signup_text);
        signinBton = findViewById(R.id.signin_bton);
        email = findViewById(R.id.signin_email);
        pass = findViewById(R.id.signin_pass);
        frame=findViewById(R.id.frame1);
        signintxt=findViewById(R.id.signin_text);

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new signupFrag());


            }
            private void loadFragment(signupFrag frag1){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame1,frag1);
                ft.commit();
            }
        });

        signintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new signinFrag());

            }
            private void loadFragment(signinFrag frag2){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame1,frag2);
                ft.commit();
            }
        });
    }
}