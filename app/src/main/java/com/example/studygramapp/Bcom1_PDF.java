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

public class Bcom1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcom1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Auditing and Corporate Governance", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FAuditing%20and%20corporate%20governance.pdf?alt=media&token=08f929f0-e2a2-4b8c-8d1e-2e7219ee0037"));

        productList.add(new Product1(2, "Banking and Insurance ", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FBanking%20and%20Insurance.pdf?alt=media&token=3be6aead-5094-45e1-ba00-f9efee8a848d "));

        productList.add(new Product1(3, "Business Accounting Software ", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FBusiness%20accounting%20software.pdf?alt=media&token=875f3bae-64f5-4d16-aeab-fe19ae28d967"));


        productList.add(new Product1(4, "Corporate Tax Planning", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FCorporate%20tax%20planning.pdf?alt=media&token=391e98f4-2701-41c7-bb15-7d0ecc434d30"));


        productList.add(new Product1(5, "Digital banking", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FDigital%20banking.pdf?alt=media&token=1d590bff-89fa-4832-ac91-a1288a058939"));


        productList.add(new Product1(6, "Financial Accounting", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FFinancial%20accounting.pdf?alt=media&token=1defc13b-869a-4998-99cf-5f1f7771c8e5"));

        productList.add(new Product1(7, "Fundamentals of Computer", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FFundamentals%20of%20Computers.pdf?alt=media&token=b3650748-a325-49b1-ac67-af08a61a194a"));

        productList.add(new Product1(8, "Fundamentals of Investment", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2FFundamentals%20of%20investment.pdf?alt=media&token=f220d278-d77f-4e9a-b679-f19d2da2bfd7"));


        productList.add(new Product1(9, "Cost Accounting ", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bcom%2Fcost%20accounting.pdf?alt=media&token=b0063784-946e-4d01-beee-277acd203bda"));


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
