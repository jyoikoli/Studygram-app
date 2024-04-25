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

public class BAF1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baf1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "Auditing Introduction and Planning", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FAuditing-Introduction-and-Planning.pdf?alt=media&token=1ce030f9-4a4e-49e8-9c21-ad1bac8564e7"));

        productList.add(new Product1(2, "Business Law", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FBUSINESS%20LAW.pdf?alt=media&token=2d603757-06b2-4038-bca8-8908288f43ec"));

        productList.add(new Product1(3, "Business Communication", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FBusiness%20Communication.pdf?alt=media&token=8e98a8b4-62bf-4c4e-93cc-513f7c9e1af4"));


        productList.add(new Product1(4, "Business Environment", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FCommercial%20Environment.pdf?alt=media&token=8fbff0f6-1822-4003-bb54-da555e3a6ae6"));


        productList.add(new Product1(5, "Cost Accounting", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FCost%20Accounting-Introduction%20and%20Element%20of%20Cost.pdf?alt=media&token=49bc44a4-1808-4d3a-b877-184dd0e2676a"));


        productList.add(new Product1(6, "Micro Economics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FEconomics-Micro%20Economics-.pdf?alt=media&token=01d13027-318a-4170-a5dc-f7250f04da8b"));

        productList.add(new Product1(7, "Quantitative", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FQUANTITATIVE.pdf?alt=media&token=8e44b153-3aff-4d6a-b5c2-60c36779d4e7"));

        productList.add(new Product1(8, "Taxation Indirect Taxes", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/BAF%2FTaxation-Indirect%20Taxes.pdf?alt=media&token=1b5b6808-4f3a-46f4-a4c8-1e2535b32cfa"));




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
