package com.example.studygramapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationAdapter.ConsultationViewHolder> {
    private List<Consultation> consultationList;

    public ConsultationAdapter(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    @NonNull
    @Override
    public ConsultationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultant, parent, false);
        return new ConsultationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultationViewHolder holder, int position) {
        Consultation consultation = consultationList.get(position);
        holder.bind(consultation);
    }

    @Override
    public int getItemCount() {
        return consultationList.size();
    }

    public class ConsultationViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDoubt;
        private TextView textViewDate;
        private TextView textViewTime;

        public ConsultationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDoubt = itemView.findViewById(R.id.textViewDoubt);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }

        public void bind(Consultation consultation) {
            textViewName.setText("Name: " + consultation.getName());
            textViewDoubt.setText("Doubt: " + consultation.getDoubt());
            textViewDate.setText("Date: " + consultation.getDate());
            textViewTime.setText("Time: " + consultation.getTime());
        }

    }
}
