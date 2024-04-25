package com.example.studygramapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ConsultationAdapter2 extends RecyclerView.Adapter<ConsultationAdapter2.ConsultationViewHolder> {

    private List<Consultation2> consultationList;
    private DatabaseReference databaseReference;

    public ConsultationAdapter2() {
        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Responses");
    }

    public void updateData(List<Consultation2> consultationList) {
        this.consultationList = consultationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ConsultationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_retrieve_response, parent, false);
        return new ConsultationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultationViewHolder holder, int position) {
        Consultation2 consultation = consultationList.get(position);
        holder.bind(consultation);
    }

    @Override
    public int getItemCount() {
        return consultationList != null ? consultationList.size() : 0;
    }

    public class ConsultationViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDoubt;
        private TextView textViewTeacherResponse;
        private EditText editTextTeacherResponse;
        private Button buttonGiveResponse1;

        public ConsultationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDoubt = itemView.findViewById(R.id.textViewDoubt);
            editTextTeacherResponse = itemView.findViewById(R.id.editTextResponse);
            buttonGiveResponse1 = itemView.findViewById(R.id.buttonGiveResponse);

            // Set up the click listener for the button
            buttonGiveResponse1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Consultation2 consultation = consultationList.get(position);
                        String teacherResponse = editTextTeacherResponse.getText().toString().trim();

                        if (!teacherResponse.isEmpty()) {
                            // Update the model
                            consultation.setTeacherResponse(teacherResponse);

                            // Update Firebase Database
                            databaseReference.child(consultation.getKey()).setValue(consultation, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError error, @NonNull DatabaseReference ref) {
                                    if (error == null) {
                                        // Notify the user that the response was successfully stored
                                        Toast.makeText(itemView.getContext(), "Response stored successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Handle the error
                                        Toast.makeText(itemView.getContext(), "Error storing response: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // Handle empty response
                            Toast.makeText(itemView.getContext(), "Please enter a response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        public void bind(Consultation2 consultation) {
            textViewName.setText("Name: " + consultation.getName());
            textViewDoubt.setText("Doubt: " + consultation.getDoubt());
            textViewTeacherResponse.setText("Teacher's Response: " + consultation.getTeacherResponse());
        }
    }
}
