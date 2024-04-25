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

public class Chemistry1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Atomic Structure", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Chemistry%20FY%2FAtomic%20structure.pdf?alt=media&token=14a5a9a9-b8da-4395-bee0-0618ee5157b0"));

        productList.add(new Product1(2, "Biochemistry", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Chemistry%20FY%2FBiochemistry.pdf?alt=media&token=d6765aab-a52c-41c2-a7cd-40fcf85ee4fb"));

        productList.add(new Product1(3, "Chemical Bonding", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Chemistry%20FY%2FChemical%20Bonding.pdf?alt=media&token=46e7ad3a-0d78-4aea-97d4-32462ab2d487 "));


        productList.add(new Product1(4, "Environmental Chemistry", 60000, R.drawable.pdf,
                " https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Chemistry%20FY%2FEnvironmental%20Chemistry.pdf?alt=media&token=f82dca6e-4d48-44a5-935b-66cee36557f5"));


        productList.add(new Product1(5, "Inorganic Chemistry", 60000, R.drawable.pdf,
                " https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/Bsc_Chemistry%20FY%2FINORGANIC%20CHEMISTRY.pdf?alt=media&token=d6cf414c-7fd9-4560-9d36-f03ff5877dc6"));



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