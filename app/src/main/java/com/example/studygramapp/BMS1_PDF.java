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

public class BMS1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bms1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Brand Management", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FBrand%20management.pdf?alt=media&token=212ae32b-dee5-4e70-8dcf-1871f6b7b92f"));

        productList.add(new Product1(2, "Environmental Management", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FEnvironmental%20Management.pdf?alt=media&token=c943113c-d88a-4f58-8478-51ddcf998a84 "));

        productList.add(new Product1(3, "Finance", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FFinance.pdf?alt=media&token=0f8e4a85-62f3-44ba-8925-b87c0acf309a "));


        productList.add(new Product1(4, "Foundation of Human Skills", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FFoundation%20of%20Human%20Skills.pdf?alt=media&token=bfa350cf-58d6-4414-8b28-e023fa490803 "));


        productList.add(new Product1(5, "International Marketing", 60000, R.drawable.pdf,
                " https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FINTERNATIONAL-MARKETING.pdf?alt=media&token=f0f0c7bc-114c-4d04-b45c-d196135e7637"));


        productList.add(new Product1(6, "Industrial Relations", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FIndustrial%20relations.pdf?alt=media&token=84146cfe-54e4-4d31-8d92-24981f78dd51 "));

        productList.add(new Product1(7, "Management Accounting", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FManagement%20accounting.pdf?alt=media&token=e7d8de0f-db64-4e48-9238-c238b6925408 "));

        productList.add(new Product1(8, "Fundamental of Research Methodology and Statistics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FResearch%20methodology1.pdf?alt=media&token=03ab5321-cca7-4811-aab1-9d7bbd7546fc "));


        productList.add(new Product1(9, "Risk management", 60000, R.drawable.pdf,
                " https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2FRisk%20management.pdf?alt=media&token=a147e72f-ce3b-46f4-b280-71b8e2e0db22"));


        productList.add(new Product1(10, "Digital Marketing", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMS%2Fdigital-marketing.pdf?alt=media&token=2603a695-9622-4621-84c3-efff6992da1b "));


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
