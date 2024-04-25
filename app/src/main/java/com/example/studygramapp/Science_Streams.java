package com.example.studygramapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Science_Streams extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.one, R.drawable.two, R.drawable.three};
    ImageView first;
    ImageView second;
    ImageView third;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_streams);
        sliderView = findViewById(R.id.image_slider);

        ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        final ImageView phy1 = findViewById(R.id.phy);
        final ImageView chem1 = findViewById(R.id.chem);
        final ImageView bio1 = findViewById(R.id.bio);
        final ImageView IT2 = findViewById(R.id.IT);
        final ImageView maths1 = findViewById(R.id.maths);



        phy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Science_Streams.this, Physics1_PDF.class);
                startActivity(intent);
            }
        });

        chem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Science_Streams.this, Chemistry1_PDF.class);
                startActivity(intent);
            }
        });

        bio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Science_Streams.this, Biology1_PDF.class);
                startActivity(intent);
            }
        });

        IT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Science_Streams.this, InfoTech1_PDF.class);
                startActivity(intent);
            }
        });

        maths1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Science_Streams.this, Maths1_PDF.class);
                startActivity(intent);
            }
        });
    }
}
