package com.example.studygramapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterasStudent extends AppCompatActivity {
    com.google.firebase.database.DatabaseReference DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mydatabase-b4707-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeras_student);

        final EditText username = findViewById(R.id.username);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);

        final EditText pass = findViewById(R.id.pass);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(R.id.S_Register);
        final TextView loginNowBtn = findViewById(R.id.S_Login_Now);

        final ImageButton toggleButtonShowPassword = findViewById(R.id.toggleButtonShowPassword);
        toggleButtonShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                togglePasswordVisibility(pass, toggleButtonShowPassword);
            }
        });

        final ImageButton toggleButtonShowConfirmPassword = findViewById(R.id.toggleButtonShowConfirmPassword);
        toggleButtonShowConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle confirm password visibility
                togglePasswordVisibility(conPassword, toggleButtonShowConfirmPassword);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameTxt = username.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = pass.getText().toString();
                final String phoneTxt = phone.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                if (nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty()|| phoneTxt.isEmpty()) {
                    Toast.makeText(RegisterasStudent.this, "Enter details", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference.child("STUDENT").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || conPasswordTxt.isEmpty()) {
                                Toast.makeText(RegisterasStudent.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (!isValidEmail(emailTxt)) {
                                Toast.makeText(RegisterasStudent.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            // Check password confirmation
                            if (!passwordTxt.equals(conPasswordTxt)) {
                                Toast.makeText(RegisterasStudent.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (TextUtils.isEmpty(phoneTxt) || !Patterns.PHONE.matcher(phoneTxt).matches()) {
                                Toast.makeText(RegisterasStudent.this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            if (!isValidPhoneNumber(phoneTxt)) {
                                Toast.makeText(RegisterasStudent.this, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            // Check if the password contains at least one special character
                            if (!passwordTxt.matches(".*[@#$%^&+=].*")) {
                                Toast.makeText(RegisterasStudent.this, "Password should contain at least one special character", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            if (TextUtils.isEmpty(emailTxt) || !isValidEmail(emailTxt)) {
                                Toast.makeText(RegisterasStudent.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (phoneTxt.isEmpty()) {
                                Toast.makeText(RegisterasStudent.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // Validate password
                            if (TextUtils.isEmpty(passwordTxt) || passwordTxt.length() < 6) {
                                Toast.makeText(RegisterasStudent.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            // Check password strength (upper case, lower case, special character)
                            if (!passwordTxt.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{6,10}$")) {
                                String errorMessage = "Password should ";
                                if (passwordTxt.length() < 6 || passwordTxt.length() > 10) {
                                    errorMessage += "be between 6 and 10 characters long, ";
                                }
                                if (!passwordTxt.matches(".*[a-z].*")) {
                                    errorMessage += "contain at least one lower case letter, ";
                                }
                                if (!passwordTxt.matches(".*[A-Z].*")) {
                                    errorMessage += "contain at least one upper case letter, ";
                                }
                                if (!passwordTxt.matches(".*\\d.*")) {
                                    errorMessage += "contain at least one digit, ";
                                }
                                if (!passwordTxt.matches(".*[@#$%^&+=].*")) {
                                    errorMessage += "contain at least one special character, ";
                                }
                                Toast.makeText(RegisterasStudent.this, errorMessage, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            // Save user details in SharedPreferences
                            saveUserInfoInPrefs(nameTxt, emailTxt, phoneTxt, conPasswordTxt, passwordTxt);

                            DatabaseReference.child("STUDENT").child(phoneTxt).child("username").setValue(nameTxt);
                            DatabaseReference.child("STUDENT").child(phoneTxt).child("conPassword").setValue(conPasswordTxt);
                            DatabaseReference.child("STUDENT").child(phoneTxt).child("conPassword").setValue(conPasswordTxt);
                            DatabaseReference.child("STUDENT").child(phoneTxt).child("email").setValue(emailTxt);
                            DatabaseReference.child("STUDENT").child(phoneTxt).child("pass").setValue(passwordTxt);
                            Toast.makeText(RegisterasStudent.this, "You registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterasStudent.this, Departments.class));
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterasStudent.this, LoginasStudent.class));
            }
        });
    }
    private void saveUserInfoInPrefs(String userName, String userEmail, String userPhone, String userConPassword, String userPassword) {
        // Save user information in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", userName);
        editor.putString("UserEmail", userEmail);
        editor.putString("UserPhone", userPhone);
        editor.putString("UserConPassword", userConPassword);
        editor.putString("UserPassword", userPassword);
        editor.apply();
    }
    private void togglePasswordVisibility(EditText editText, ImageButton toggleButton) {
        // Toggle password visibility
        int inputType = editText.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            toggleButton.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggleButton.setImageResource(R.drawable.ic_baseline_visibility_24);
        }
        // Move cursor to the end of the text
        editText.setSelection(editText.getText().length());
    }
    // Update the isValidEmail method
    private boolean isValidEmail(String email) {
        // Use Android Patterns class to check if the email address has a valid format
        boolean isValidFormat = Patterns.EMAIL_ADDRESS.matcher(email).matches();

        // Check if the email address contains capital letters
        boolean containsCapitalLetters = !email.equals(email.toLowerCase());

        // Return true if the format is valid and there are no capital letters
        return isValidFormat && !containsCapitalLetters;
    }

    // Add this method to validate the phone number
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phone number is exactly 10 digits and contains only numeric characters
        return phoneNumber.length() == 10 && TextUtils.isDigitsOnly(phoneNumber);
    }


}
