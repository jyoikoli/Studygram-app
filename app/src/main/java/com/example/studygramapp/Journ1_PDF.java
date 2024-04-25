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

public class Journ1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journ1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Basics of Journalism", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Journalism%2Fbasics%20of%20Journalism.pdf?alt=media&token=89ddc098-ea8f-4e15-917f-709d93f05acb"));

        productList.add(new Product1(2, "Adavnce of Journalism", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Journalism%2FAdvanced%20journalism.pdf?alt=media&token=b817863d-8651-4aba-8368-73c104ee909c"));



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
