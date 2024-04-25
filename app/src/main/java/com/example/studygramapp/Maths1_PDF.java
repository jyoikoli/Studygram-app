package com.example.studygramapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Maths1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Applied Mechanics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FAPPLIED%20MECHANICS.pdf?alt=media&token=87e02b8d-0d37-414b-8abe-5019010fc393"));

        productList.add(new Product1(2, "Calculas", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FCalculus.pdf?alt=media&token=90f98482-7c15-45ed-a21a-e31801c3a6e4"));

        productList.add(new Product1(3, "Differential Equation", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FDIFFERENTIAL%20EQUATIONS.pdf?alt=media&token=1d299739-d088-4c47-aad5-af42c5094019"));


        productList.add(new Product1(4, "Differentiation", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FDifferentiation.pdf?alt=media&token=79e56eb3-0877-44a4-889b-8588b168fd57"));


        productList.add(new Product1(5, "Linear Programming", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FLinear%20Programming.pdf?alt=media&token=afd6e8da-b6f4-47cf-bd7c-b13b814dfc45"));


        productList.add(new Product1(6, "Mathematical Modelling", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FMathematical%20modelling.pdf?alt=media&token=e286cd57-d593-4017-a720-75a7e1ac3358"));

        productList.add(new Product1(7, "Partial Differential Equations", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FPartial%20differential%20equations.pdf?alt=media&token=1a118d58-f8c0-4ca6-8579-16955e79d201"));

        productList.add(new Product1(8, "Probability", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FProbability.pdf?alt=media&token=f9eda323-d6c4-4dd1-8da2-3da89e822abd"));


        productList.add(new Product1(9, "Statistics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Maths%2FStatistics.pdf?alt=media&token=0ab5a958-8b8a-4803-8fad-d08dde86ef58"));




        ProductAdapter1 adapter = new ProductAdapter1(this, productList, new ProductAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(Product1 item) {
                // Handle item click here, for example, open the PDF using an Intent
                openPdf(item.getPdfUrl());
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void openPdf(String pdfUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(pdfUrl));
        startActivity(intent);
    }
}
