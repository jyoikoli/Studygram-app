package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class S_Events extends AppCompatActivity {
    Button btnInsert;
    EditText name, addr, desc;
    DatabaseReference databaseEvents;
    FloatingActionButton mAddAlarmFab, mAddPersonFab;

    // Use the ExtendedFloatingActionButton to handle the
    // parent FAB
    ExtendedFloatingActionButton mAddFab;

    // These TextViews are taken to make visible and
    // invisible along with FABs except parent FAB's action
    // name
    TextView addAlarmActionText, addPersonActionText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible = false; // Set it to false initially

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevents);

        name = findViewById(R.id.C_name);
        addr = findViewById(R.id.C_addr);
        desc = findViewById(R.id.C_desc);
        btnInsert = findViewById(R.id.btninsert);

        // Register all the FABs with their appropriate IDs
        // This FAB button is the Parent
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);

        // Also register the action name text, of all the
        // FABs. except parent FAB action name text
        addAlarmActionText = findViewById(R.id.add_alarm_action_text);
        addPersonActionText = findViewById(R.id.add_person_action_text);

        // Change this line to point to the correct location in your Firebase Realtime Database
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });

        // Set click listeners for FABs
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFABs();
            }
        });

        mAddPersonFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Your action for add person FAB
                // For example, open a new activity
                Intent intent = new Intent(S_Events.this, StudentDoubtsView.class);
                startActivity(intent);
            }
        });

        mAddAlarmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Your action for add alarm FAB
                // For example, open a new activity
                Intent intent = new Intent(S_Events.this, View_Events.class);
                startActivity(intent);
            }
        });
    }

    private void InsertData() {
        String username = name.getText().toString().trim();
        String useraddr = addr.getText().toString().trim();
        String userdesc = desc.getText().toString().trim();

// Your validation logic
        if (username.isEmpty() || useraddr.isEmpty() || userdesc.isEmpty()) {
            Toast.makeText(S_Events.this, "Please fill all th details", Toast.LENGTH_SHORT).show();
            return;
        }
        // Your validation logic
        if (username.isEmpty()) {
            Toast.makeText(S_Events.this, "Please Enter Company Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (useraddr.isEmpty()) {
            Toast.makeText(S_Events.this, "Please Enter Company Address", Toast.LENGTH_SHORT).show();
            return;
        }
        // Your validation logic
        if (userdesc.isEmpty()) {
            Toast.makeText(S_Events.this, "Please Enter Company Description", Toast.LENGTH_SHORT).show();
            return;
        }


        // Create a unique key for each event using push()
        String eventId = databaseEvents.push().getKey();

        // Create an Event object with the provided data
        User event = new User(username, useraddr, userdesc);

        // Save the event to the database using the eventId as the key
        databaseEvents.child(eventId).setValue(event);

        // Display a toast message
        Toast.makeText(S_Events.this, "Submitted job opportunities", Toast.LENGTH_SHORT).show();

        // Clear the form
        clearForm();

        // If data is successfully inserted, set isAllFabsVisible to true
        isAllFabsVisible = true;
        toggleFABs();
    }

    private void clearForm() {
        // Clear the EditText fields
        name.setText("");
        addr.setText("");
        desc.setText("");
    }

    private void toggleFABs() {
        if (isAllFabsVisible) {
            // If FABs are visible, hide them
            mAddAlarmFab.hide();
            mAddPersonFab.hide();
            addAlarmActionText.setVisibility(View.GONE);
            addPersonActionText.setVisibility(View.GONE);
            mAddFab.shrink();
        } else {
            // If FABs are not visible, show them
            mAddAlarmFab.show();
            mAddPersonFab.show();
            addAlarmActionText.setVisibility(View.VISIBLE);
            addPersonActionText.setVisibility(View.VISIBLE);
            mAddFab.extend();
        }

        // Toggle the boolean variable
        isAllFabsVisible = !isAllFabsVisible;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_logout) {
            // Handle Logout menu item click
            // Perform logout actions and navigate to the login page
            Intent intent = new Intent(S_Events.this, LoginasTeacher.class);
            startActivity(intent);
            finish(); // Close the current activity to prevent going back
            return true;
        }
        if (id == R.id.viewstudents) {
            // Handle Logout menu item click
            // Perform logout actions and navigate to the login page
            Intent intent = new Intent(S_Events.this, RegisteredStudents.class);
            startActivity(intent);
            // Close the current activity to prevent going back
            return true;
        }
        if (id == R.id.viewresponse) {
            // Handle Logout menu item click
            // Perform logout actions and navigate to the login page
            Intent intent = new Intent(S_Events.this, ClickforResponse.class);
            startActivity(intent);
            // Close the current activity to prevent going back
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
