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

public class CommerceDept extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.one,
            R.drawable.two,
            R.drawable.three};
    ImageView first;
    ImageView second;
    ImageView third;
    FloatingActionButton floatingActionButton;

    ImageView BAF1;
    ImageView BBA1;
    ImageView BMS1;
    ImageView Bcom1;
    ImageView BMM1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce_dept);

        sliderView = findViewById(R.id.image_slider);

        ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        BAF1 = findViewById(R.id.baf);
        BBA1=findViewById(R.id.bba);
        BMS1 = findViewById(R.id.bms);
        Bcom1=findViewById(R.id.bcom);
        BMM1 = findViewById(R.id.bmm);

        BAF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(CommerceDept.this, BAF1_PDF.class);
                startActivity(intent);
            }
        });
        BBA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(CommerceDept.this, BBA1_PDF.class);
                startActivity(intent);
            }
        });
        BMS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(CommerceDept.this, BMS1_PDF.class);
                startActivity(intent);
            }
        });
        Bcom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(CommerceDept.this, Bcom1_PDF.class);
                startActivity(intent);
            }
        });

        BMM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(CommerceDept.this, BMM1_PDF.class);
                startActivity(intent);
            }
        });
    }
}
