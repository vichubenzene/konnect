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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupFrag extends Fragment {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText name,email,pass;
    Button signupBton;
    Users user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup,container,false);
        signupBton = view.findViewById(R.id.signup_bton);
        name = view.findViewById(R.id.signup_name);
        email = view.findViewById(R.id.signup_email);
        pass = view.findViewById(R.id.signup_pass);
        mAuth = FirebaseAuth.getInstance();
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");
        signupBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    database = FirebaseDatabase.getInstance();
                                    reference = database.getReference("Users");
                                    user = new Users(name.getText().toString(),email.getText().toString(),pass.getText().toString());
                                    String id = task.getResult().getUser().getUid();
                                    reference.child(id).setValue(user);
                                    Intent intent = new Intent(getContext(),MainActivity.class);
                                    startActivity(intent);

                                }
                                else{
                                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }


                            }
                        });
            }
        });
        return view;
    }
}