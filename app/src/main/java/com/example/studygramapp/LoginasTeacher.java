package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginasTeacher extends AppCompatActivity {
    com.google.firebase.database.DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mydatabase-b4707-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginas_teacher);

        final EditText phone=findViewById(R.id.T_phone);
        final EditText password=findViewById(R.id.T_password);
        final Button loginbtn=findViewById(R.id.T_Login);
        final TextView registerNowBtn=findViewById(R.id.T_Reg_Now);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt=phone.getText().toString();
                final String passwordTxt=password.getText().toString();

                // Validate phone number
                if (TextUtils.isEmpty(phoneTxt)) {
                    Toast.makeText(LoginasTeacher.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phoneTxt.isEmpty()||passwordTxt.isEmpty()){
                    Toast.makeText(LoginasTeacher.this,"Please enter your email or password", Toast.LENGTH_SHORT).show();
                }

                else{
                    databaseReference.child("PROFESSOR").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)) {
                                final String getPassword = snapshot.child(phoneTxt).child("pass").getValue(String.class);
                                if (getPassword != null && getPassword.equals(passwordTxt)) {
                                    Toast.makeText(LoginasTeacher.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginasTeacher.this, S_Events.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginasTeacher.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginasTeacher.this, "Wrong Phone Number", Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginasTeacher.this, RegisterasTeacher.class));
            }
        });


    }
}