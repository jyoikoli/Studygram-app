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

public class Biology1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biology1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Cell Biology", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Biology%20FY%2FCell%20Biology.pdf?alt=media&token=7ea87eea-2092-4cbb-8070-9cc10a8c070b "));

        productList.add(new Product1(2, "MicroBiology", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Biology%20FY%2FMicrobiology.pdf?alt=media&token=14cf66ec-ab70-43b0-b17d-3ce253fc91a4 "));

        productList.add(new Product1(3, "Plant Physiology", 60000, R.drawable.pdf,
                " https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Biology%20FY%2FPlant%20Physiology.pdf?alt=media&token=e49de1a0-2ada-4f43-92af-388e70c9375f "));


        productList.add(new Product1(4, "Zoology", 60000, R.drawable.pdf,
                " https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Biology%20FY%2FZoology.pdf?alt=media&token=ce8b83cb-905b-41c2-9ad5-2b07def3b215 "));




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