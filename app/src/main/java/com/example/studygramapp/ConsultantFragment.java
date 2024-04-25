package com.example.studygramapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ConsultantFragment extends Fragment {

    private EditText editTextName, editTextDoubt, editTextTeacherEmail;
    private TextView textViewDate, textViewTime;
    private Button buttonSelectDate, buttonSelectTime, buttonSubmit;
    private Calendar selectedDate = Calendar.getInstance();
    private Calendar selectedTime = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultant, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextDoubt = view.findViewById(R.id.editTextDoubt);
        editTextTeacherEmail = view.findViewById(R.id.editTextTeacherEmail);
        textViewDate = view.findViewById(R.id.textViewDate);
        textViewTime = view.findViewById(R.id.textViewTime);
        buttonSelectDate = view.findViewById(R.id.buttonSelectDate);
        buttonSelectTime = view.findViewById(R.id.buttonSelectTime);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);


        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        return view;
    }


    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(Calendar.YEAR, year);
                        selectedDate.set(Calendar.MONTH, month);
                        selectedDate.set(Calendar.DAY_OF_MONTH, day);

                        // Check if the selected date is today or in the future
                        if (!selectedDate.before(Calendar.getInstance())) {
                            ConsultantFragment.this.selectedDate = selectedDate;
                            updateDateTextView();
                        } else {
                            Toast.makeText(requireContext(), "Please select today or a future date", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );

        // Set the minimum date to tomorrow
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Calendar selectedTime = Calendar.getInstance();
                        selectedTime.set(Calendar.HOUR_OF_DAY, hour);
                        selectedTime.set(Calendar.MINUTE, minute);

                        // Check if the selected time is within the allowed range (8 am to 10 pm)
                        Calendar minTime = Calendar.getInstance();
                        minTime.set(Calendar.HOUR_OF_DAY, 8);
                        minTime.set(Calendar.MINUTE, 0);

                        Calendar maxTime = Calendar.getInstance();
                        maxTime.set(Calendar.HOUR_OF_DAY, 22);
                        maxTime.set(Calendar.MINUTE, 0);

                        if (selectedTime.after(minTime) && selectedTime.before(maxTime)) {
                            ConsultantFragment.this.selectedTime = selectedTime;
                            updateTimeTextView();
                        } else {
                            Toast.makeText(requireContext(), "Please select a time between 8 am and 10 pm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                false
        );

        timePickerDialog.show();
    }

    private void updateDateTextView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateStr = dateFormat.format(selectedDate.getTime());
        textViewDate.setText("Selected Date: " + dateStr);
    }

    private void updateTimeTextView() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeStr = timeFormat.format(selectedTime.getTime());
        textViewTime.setText("Selected Time: " + timeStr);
    }

    private void submitForm() {
        String name = editTextName.getText().toString();
        String doubt = editTextDoubt.getText().toString();
        String teacherEmail = editTextTeacherEmail.getText().toString();
        String dateStr = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedDate.getTime());
        String timeStr = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(selectedTime.getTime());

        // Validate that name, doubt, date, time, and teacher's email are not empty
        if (name.isEmpty() || doubt.isEmpty() || selectedDate == null || selectedTime == null || teacherEmail.isEmpty()) {
            if (name.isEmpty()) {
                editTextName.setError("Please enter the name");
            }
            if (doubt.isEmpty()) {
                editTextDoubt.setError("Please enter the doubt");
            }
            if (selectedDate == null) {
                Toast.makeText(requireContext(), "Please select a date", Toast.LENGTH_SHORT).show();
            }
            if (selectedTime == null) {
                Toast.makeText(requireContext(), "Please select a time", Toast.LENGTH_SHORT).show();
            }
            if (teacherEmail.isEmpty()) {
                editTextTeacherEmail.setError("Please enter the teacher's email");
            }
            return;
        }

        // Store the doubt in Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Consultations");
        String key = databaseReference.push().getKey();
        Consultation doubtObj = new Consultation(name, doubt, dateStr, timeStr, teacherEmail);
        databaseReference.child(key).setValue(doubtObj);

        // Notify the user
        Toast.makeText(requireContext(), "Doubt submitted successfully", Toast.LENGTH_SHORT).show();

        // Clear the form fields and errors
        editTextName.setText("");
        editTextDoubt.setText("");
        editTextTeacherEmail.setText("");
        textViewDate.setText("Select Date");
        textViewTime.setText("Select Time");
        editTextName.setError(null);
        editTextDoubt.setError(null);
        editTextTeacherEmail.setError(null);


    }
}
