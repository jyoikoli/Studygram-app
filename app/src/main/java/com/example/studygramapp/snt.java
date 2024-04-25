package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class snt extends AppCompatActivity {
    Button student1;
    Button teacher1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snt);
        student1=findViewById(R.id.student);
        student1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterasStudent.class));
            }
        });
        teacher1=findViewById(R.id.teacher);
        teacher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterasTeacher.class));
            }
        });
    }
}