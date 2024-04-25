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

public class BMM1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmm1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Content Writing", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FContent%20writing.pdf?alt=media&token=2fbb4494-bd6c-42e0-9dca-aace84b0490f "));

        productList.add(new Product1(2, "Effective Communication Skills", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FEffective%20communication%20skills.pdf?alt=media&token=e3cf8659-2e00-49fc-9bdf-88d3c02597a7"));

        productList.add(new Product1(3, "Intoduction to Sociology", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FINTRODUCTION%20TO%20SOCIOLOGY.pdf?alt=media&token=64c06e3a-1c41-4b25-b75d-7a5acccb36b2"));


        productList.add(new Product1(4, "Introduction to Advertising", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FIntroduction%20to%20advertising.pdf?alt=media&token=b0e6d25e-daff-4d7c-bcbb-947ee92372b5"));


        productList.add(new Product1(5, "Introduction to Journalism", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FIntroduction%20to%20journalism.pdf?alt=media&token=938f85aa-8cb9-4faa-bc3e-7f008072610d   "));


        productList.add(new Product1(6, "Media Laws and Ethics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FMedia%20laws%20and%20ethics.pdf?alt=media&token=233e701e-9fd7-41ab-b61d-ad3c59841be8"));

        productList.add(new Product1(7, "Political Concepts and Indian Political System", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FPolitical%20concepts%20and%20indian%20political%20system.pdf?alt=media&token=779e5e3e-caf1-4d15-8875-fcf97c55a578"));

        productList.add(new Product1(8, "Public Relations", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BMM%2FPublic%20relations.pdf?alt=media&token=7ed5f337-24af-4c62-88e7-edbc67491fab"));

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
