package com.example.konnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signinFrag extends Fragment {
    Button signinBton;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    EditText email,pass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin,container,false);
        signinBton = view.findViewById(R.id.signin_bton);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Logging in to your account");
        mAuth=FirebaseAuth.getInstance();
        email = view.findViewById(R.id.signin_email);
        pass = view.findViewById(R.id.signin_pass);
        signinBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });
        return view;
    }
}