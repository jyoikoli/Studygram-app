package com.example.studygramapp;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentRetrieveResponse extends AppCompatActivity {

    private TextView studentDoubtTextView, meetLinkTextView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_retrieve_response);

        // Initialize the DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("Responded Students Doubts");

        studentDoubtTextView = findViewById(R.id.studentDoubtTextView);
        meetLinkTextView = findViewById(R.id.meetLinkTextView);


        // Retrieve all doubts and responses
        retrieveAllDoubts();
    }

    private void retrieveAllDoubts() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Clear previous content
                    studentDoubtTextView.setText("");
                    meetLinkTextView.setText("");

                    // Iterate through all retrieved doubts and responses
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String studentDoubt = snapshot.child("doubt").getValue(String.class);
                        String meetLink = snapshot.child("meetLink").getValue(String.class);

                        // Concatenate doubt and meet link or response
                        String doubtAndResponse = "Doubt: " + studentDoubt + "\n\n" +
                                "Meeting Link or Response: " + meetLink + "\n";

                        // Append each doubt and response on the same line
                        studentDoubtTextView.append(doubtAndResponse);
                    }
                } else {
                    // Handle case where no doubts are found
                    studentDoubtTextView.setText("No doubts found");
                    meetLinkTextView.setText("No doubts found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}