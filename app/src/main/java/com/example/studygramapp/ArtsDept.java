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

public class ArtsDept extends AppCompatActivity {


    SliderView sliderView;
    int[] images = {R.drawable.one,
            R.drawable.two,
            R.drawable.three};
    ImageView first;
    ImageView second;
    ImageView third;
    FloatingActionButton floatingActionButton;

    ImageView eng1;
    ImageView polsci1;
    ImageView physco1;
    ImageView philo1;
    ImageView journal1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts_dept);

        sliderView = findViewById(R.id.image_slider);

        ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        eng1 = findViewById(R.id.eng);
        polsci1=findViewById(R.id.ps);
        physco1 = findViewById(R.id.physco);
        philo1=findViewById(R.id.philo);
        journal1 = findViewById(R.id.journ);


        eng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(ArtsDept.this, Eng1_PDF.class);
                startActivity(intent);
            }
        });
        polsci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(ArtsDept.this, PS1_PDF.class);
                startActivity(intent);
            }
        });
        physco1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(ArtsDept.this, Physco1_PDF.class);
                startActivity(intent);
            }
        });
        philo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(ArtsDept.this, Philo1_PDF.class);
                startActivity(intent);
            }
        });

        journal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent class will help to go to next activity using
                // it's object named intent.
                // SecondActivty is the name of new created EmptyActivity.
                Intent intent = new Intent(ArtsDept.this, Journ1_PDF.class);
                startActivity(intent);
            }
        });
    }
}
