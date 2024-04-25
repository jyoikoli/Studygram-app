package com.example.studygramapp;

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

public class RegisterasTeacher extends AppCompatActivity {
    com.google.firebase.database.DatabaseReference DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mydatabase-b4707-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeras_teacher);

        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.T_pass);
        final EditText phone = findViewById(R.id.T_Phone);
        final EditText conPassword = findViewById(R.id.T_conPassword);
        Button next = findViewById(R.id.T_next);
        TextView loginNowBtn = findViewById(R.id.T_Login_Now);

        final ImageButton toggleButtonShowPassword = findViewById(R.id.T_toggleButtonShowPassword);
        toggleButtonShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                togglePasswordVisibility(pass, toggleButtonShowPassword);
            }
        });

        final ImageButton toggleButtonShowConfirmPassword = findViewById(R.id.T_toggleButtonShowConfirmPassword);
        toggleButtonShowConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle confirm password visibility
                togglePasswordVisibility(conPassword, toggleButtonShowConfirmPassword);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameTxt = username.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = pass.getText().toString();
                final String phoneTxt = phone.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                if (nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty()|| phoneTxt.isEmpty()) {
                    Toast.makeText(RegisterasTeacher.this, "Enter details", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference.child("PROFESSOR").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Check if any field is empty
                            if (nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || conPasswordTxt.isEmpty()) {
                                Toast.makeText(RegisterasTeacher.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (!isValidEmail(emailTxt)) {
                                Toast.makeText(RegisterasTeacher.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            // Check password confirmation
                            if (!passwordTxt.equals(conPasswordTxt)) {
                                Toast.makeText(RegisterasTeacher.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (TextUtils.isEmpty(phoneTxt) || !Patterns.PHONE.matcher(phoneTxt).matches()) {
                                Toast.makeText(RegisterasTeacher.this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            if (!isValidPhoneNumber(phoneTxt)) {
                                Toast.makeText(RegisterasTeacher.this, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            // Check if the password contains at least one special character
                            if (!passwordTxt.matches(".*[@#$%^&+=].*")) {
                                Toast.makeText(RegisterasTeacher.this, "Password should contain at least one special character", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            if (TextUtils.isEmpty(emailTxt) || !isValidEmail(emailTxt)) {
                                Toast.makeText(RegisterasTeacher.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (phoneTxt.isEmpty()) {
                                Toast.makeText(RegisterasTeacher.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // Validate password
                            if (TextUtils.isEmpty(passwordTxt) || passwordTxt.length() < 6) {
                                Toast.makeText(RegisterasTeacher.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(RegisterasTeacher.this, errorMessage, Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                DatabaseReference.child("PROFESSOR").child(phoneTxt).child("username").setValue(nameTxt);
                                DatabaseReference.child("PROFESSOR").child(phoneTxt).child("conPassword").setValue(conPasswordTxt);
                                DatabaseReference.child("PROFESSOR").child(phoneTxt).child("email").setValue(emailTxt);
                                DatabaseReference.child("PROFESSOR").child(phoneTxt).child("pass").setValue(passwordTxt);
                                Toast.makeText(RegisterasTeacher.this, "You registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterasTeacher.this, TeacherDetails.class));
                                finish();
                            }
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
                startActivity(new Intent(RegisterasTeacher.this, LoginasTeacher.class));
            }
        });
    }
    private void togglePasswordVisibility(EditText editText, ImageButton toggleButton) {
        // Toggle password visibility
        if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggleButton.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        } else {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
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

    private void saveUserInfo(String name, String email) {
        // Save user information in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", name);
        editor.putString("UserEmail", email);
        editor.apply();
    }
}
