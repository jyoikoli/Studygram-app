package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class ViewRegisteredTeachers extends AppCompatActivity {
    Button recylerviewbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_teachers);

        recylerviewbtn = findViewById(R.id.recyclerviewbtn);
        recylerviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewRegisteredTeachers.this,RegisteredTeachers.class);
                startActivity(i);
                finish();


            }
        });
    }
}

