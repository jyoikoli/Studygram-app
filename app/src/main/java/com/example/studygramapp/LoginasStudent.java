package com.example.studygramapp;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginasStudent extends AppCompatActivity {
    com.google.firebase.database.DatabaseReference DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mydatabase-b4707-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginas_student);

        final EditText phone = findViewById(R.id.S_phone);
        final EditText password = findViewById(R.id.S_password);
        final Button loginBtn = findViewById(R.id.S_Login);
        final TextView registerNowBtn = findViewById(R.id.S_Reg_Now);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = phone.getText().toString().trim();
                final String passwordTxt = password.getText().toString().trim();

                // Validate phone number and password
                if (TextUtils.isEmpty(phoneTxt) || TextUtils.isEmpty(passwordTxt)) {
                    Toast.makeText(LoginasStudent.this, "Please enter both phone number and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference.child("STUDENT").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phoneTxt)) {
                            final String storedPassword = snapshot.child(phoneTxt).child("pass").getValue(String.class);
                            final String storedUsername = snapshot.child(phoneTxt).child("username").getValue(String.class);
                            final String storedEmail = snapshot.child(phoneTxt).child("email").getValue(String.class);

                            if (TextUtils.equals(storedPassword, passwordTxt)) {
                                // Save user details in SharedPreferences
                                saveUserInfoInPrefs(phoneTxt, storedUsername, storedEmail);

                                Toast.makeText(LoginasStudent.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginasStudent.this, Departments.class));
                                finish();
                            } else {
                                Toast.makeText(LoginasStudent.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginasStudent.this, "Wrong Phone Number", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled
                    }
                });
            }
        });

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginasStudent.this, RegisterasStudent.class));
            }
        });
    }

    private void saveUserInfoInPrefs(String userPhone, String userName, String userEmail) {
        // Save user information in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserPhone", userPhone);
        editor.putString("UserName", userName);
        editor.putString("UserEmail", userEmail);
        editor.apply();
    }
}
