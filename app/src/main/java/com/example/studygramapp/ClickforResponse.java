package com.example.studygramapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClickforResponse extends AppCompatActivity {

    private EditText doubtsEditText, meetLinkEditText;
    private Button submitButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickfor_response);

        // Initialize the DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("Responded Students Doubts");

        doubtsEditText = findViewById(R.id.doubtsEditText);
        meetLinkEditText = findViewById(R.id.meetLinkEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDoubt();
            }
        });
    }

    private void submitDoubt() {
        // Generate a unique key for each doubt entry
        String doubtId = databaseReference.push().getKey();

        String studentDoubt = doubtsEditText.getText().toString().trim();
        String meetLink = meetLinkEditText.getText().toString().trim();

        DatabaseReference studentDoubtRef = databaseReference.child(doubtId);
        if (studentDoubt.isEmpty() || meetLink.isEmpty()) {
            Toast.makeText(ClickforResponse.this, "Please enter both doubt and Meeting link", Toast.LENGTH_SHORT).show();
            return; // Exit the method if validation fails
        }
        if (studentDoubt.isEmpty()) {
            Toast.makeText(ClickforResponse.this, "Please enter Student doubt", Toast.LENGTH_SHORT).show();
            return; // Exit the method if validation fails
        }
        if (meetLink.isEmpty()) {
            Toast.makeText(ClickforResponse.this, "Please enter Meeting Link or Give response", Toast.LENGTH_SHORT).show();
            return; // Exit the method if validation fails
        }
        studentDoubtRef.child("doubt").setValue(studentDoubt);
        studentDoubtRef.child("meetLink").setValue(meetLink);
        Toast.makeText(ClickforResponse.this, "Response submitted successfully", Toast.LENGTH_SHORT).show();

        // You can add more fields or customize the structure based on your needs

        // Clear input fields
        doubtsEditText.setText("");
        meetLinkEditText.setText("");
    }
}