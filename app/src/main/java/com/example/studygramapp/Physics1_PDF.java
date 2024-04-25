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

public class Physics1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "ElectroMagnetism", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_FY%2FElectroMagnetism.pdf?alt=media&token=a7cff135-c769-494b-a5ce-3748c7aea25b"));

        productList.add(new Product1(2, "Quantum Mechanics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_FY%2Fquantum%20mechanics.pdf?alt=media&token=915e196c-f9ab-4416-aa3e-20ccd0359510"));

        productList.add(new Product1(3, "Thermal Physics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_FY%2FThermal%20Physics.pdf?alt=media&token=907fac29-fa11-4b36-9ce2-8d4c7e4fac3a"));


        productList.add(new Product1(4, "Nuclear Physics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_FY%2FNuclear%20Physics.pdf?alt=media&token=b1044a73-f74d-4712-93b1-882525999169"));


        productList.add(new Product1(5, "ThermoDynamics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_FY%2FThermodynamics.pdf?alt=media&token=aa1cebc1-9deb-48a2-8f35-29dc69f6987c"));


        productList.add(new Product1(6, "Astronomy", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_SY%2FAstronomy.pdf?alt=media&token=745d5308-9ada-496d-a8d8-99caf3a1a867"));

        productList.add(new Product1(7, "BioPhysics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_SY%2FBioPhysics.pdf?alt=media&token=c9b5e09e-6d3b-4ecc-a832-de082bfac1ba"));

        productList.add(new Product1(8, "Classical Mechanics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_SY%2FClassical%20Mechanics.pdf?alt=media&token=e99b6485-5111-4aa3-a50b-e117e8b0cd9b"));


        productList.add(new Product1(9, "GeoPhysics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_SY%2FGeoPhysics.pdf?alt=media&token=ee21874b-c787-4291-8f63-c32d2154bc0a"));


        productList.add(new Product1(10, "Statistical Physics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Physics_SY%2FStatistical_physics.pdf?alt=media&token=7ee78613-78a2-452c-86e6-8a868dcde4e5"));


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