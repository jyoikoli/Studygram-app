package com.example.studygramapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentDoubtsView extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    ConsultationAdapter consultationAdapter;
    ArrayList<Consultation> list;

    private EditText editTextResponse;
    private Button buttonGiveResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_doubts_view);

        recyclerView = findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance().getReference("Consultations");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        consultationAdapter = new ConsultationAdapter(list);
        recyclerView.setAdapter(consultationAdapter);

        editTextResponse = findViewById(R.id.editTextResponse);
        buttonGiveResponse = findViewById(R.id.buttonGiveResponse);

        buttonGiveResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentDoubtsView.this, "Response sent successfully", Toast.LENGTH_SHORT).show();
                // Clear the response field
                editTextResponse.getText().clear();
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Consultation consultation = dataSnapshot.getValue(Consultation.class);
                    if (consultation != null) {
                        list.add(consultation);
                    }
                }
                consultationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

}