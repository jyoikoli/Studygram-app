package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    ImageView scienceImageView;
    ImageView infoImageView;
    ImageView artsImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find ImageViews from the layout
        scienceImageView = view.findViewById(R.id.science_stream);
        infoImageView = view.findViewById(R.id.info_stream);
        artsImageView = view.findViewById(R.id.arts_stream);

        // Set onClickListeners for each ImageView
        scienceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleScienceImageViewClick();  // Move this line before starting the new activity
                Intent intent = new Intent(getActivity(), Science_Streams.class);
                startActivity(intent);
            }
        });

        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommerceDept.class);
                startActivity(intent);
            }
        });

        artsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArtsDept.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // Method to handle the click on the science ImageView
    private void handleScienceImageViewClick() {
        // Add your logic here for handling the click on the science ImageView
        // For example, if you want to refresh the content, you can call a method or update the UI directly
    }
}