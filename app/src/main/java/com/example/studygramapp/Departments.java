package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Departments extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up BottomNavigationView item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nav_consultant:
                        fragment = new ConsultantFragment();
                        break;

                    case R.id.nav_setting:
                        fragment = new SettingFragment();
                        break;

                    case R.id.nav_placement:
                        fragment = new PlacementFragment();
                        break;
                }

                // Replace the current fragment with the selected one
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

                return true;
            }
        });

        // Set the initial fragment (HomeFragment)
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todolist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.todolist) {
            // Handle Logout menu item click
            // Perform logout actions and navigate to the login page
            Intent intent = new Intent(Departments.this, S_ToDoList.class);
            startActivity(intent);
            finish(); // Close the current activity to prevent going back
            return true;
        }
        if (id == R.id.viewresponse) {
            // Handle Logout menu item click
            // Perform logout actions and navigate to the login page
            Intent intent = new Intent(Departments.this, StudentRetrieveResponse.class);
            startActivity(intent);
            // Close the current activity to prevent going back
            return true;
        }
        if (id == R.id.registerteach) {
            // Handle Logout menu item click
            // Perform logout actions and navigate to the login page
            Intent intent = new Intent(Departments.this, ViewRegisteredTeachers.class);
            startActivity(intent);
            // Close the current activity to prevent going back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}