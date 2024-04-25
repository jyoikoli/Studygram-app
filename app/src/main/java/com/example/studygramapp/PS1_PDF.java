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

public class PS1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Introduction to Political Science", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Political%20Science%2FPolitical%20Science.pdf?alt=media&token=f2f6fa08-38d8-4dbe-ab39-3ed187f598f3"));

        productList.add(new Product1(2, "Ideas and Ideologies in Politics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Political%20Science%2FIDEAS%20AND%20IDEOLOGIES%20IN%20POLITICS.pdf?alt=media&token=fbe95aca-a2a0-4c14-8da5-01e0ca3d2623"));

        productList.add(new Product1(3, "Advance of Political Science", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Political%20Science%2Fadvance%20Political%20Science.pdf?alt=media&token=3b9dda3d-e81d-4c99-8190-c424b7e750cb"));



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
