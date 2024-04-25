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

public class BBA1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bba1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Business Analytics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FBusiness%20Analytics.pdf?alt=media&token=738aab30-bec9-4e7d-9835-353f1e22f897"));

        productList.add(new Product1(2, "Business Statistics ", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FBusiness%20statistics.pdf?alt=media&token=9a67019f-934d-45fa-9b70-4d9147aa3012"));

        productList.add(new Product1(3, "Business strategies", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FBusiness%20strategies.pdf?alt=media&token=be769e84-e977-459a-9b4f-f5ee2cfb851f"));


        productList.add(new Product1(4, "Business Mathematics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FBusiness-Mathematics-1697564796.pdf?alt=media&token=6ad58b8b-cb3b-4ab5-b310-0ca70d2c48b2"));


        productList.add(new Product1(5, "Computer Application", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FComputer%20application.pdf?alt=media&token=996b9ab3-982e-4292-86f0-e9e8e0711096"));


        productList.add(new Product1(6, "Entrepreneurship", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FEntrepreneurship.pdf?alt=media&token=1edaa8a8-3bd8-4250-b3f1-9a69bc37563e"));

        productList.add(new Product1(7, "Essentials of Management", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FEssentials%20of%20Management.pdf?alt=media&token=a5824185-7297-4e51-9b2a-3cb2afef9d5f"));

        productList.add(new Product1(8, "Financial Management", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FFinancial%20management.pdf?alt=media&token=d5937296-fa28-415e-815e-977748ce9724"));


        productList.add(new Product1(9, "Human Resource Management", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FHuman%20resource%20management.pdf?alt=media&token=0c6a92fd-2cfd-4174-9c1e-9dbd7b6b0064"));


        productList.add(new Product1(10, "Marketing", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BBA%2FMarketing.pdf?alt=media&token=e2120500-dea3-48d2-8ce5-6cbd3e0f295d"));


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
