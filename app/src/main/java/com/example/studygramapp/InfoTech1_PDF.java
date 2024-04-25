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

public class InfoTech1_PDF extends AppCompatActivity {
    List<Product1> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tech1_pdf);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        relativeLayout.startAnimation(animation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        productList.add(new Product1(1, "C++", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FC%2B%2B.pdf?alt=media&token=ecc6c5b6-c611-4457-bb61-45de93789809"));

        productList.add(new Product1(2, "Computer Networks", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FCOmputer%20Networks.pdf?alt=media&token=e3339e97-cc2e-46c7-a7c3-74d866a3925e"));

        productList.add(new Product1(1, "Computer graphics", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FComputer%20graphics.pdf?alt=media&token=ecb97b2b-ad83-4e0c-9ba2-4b0a33658d95"));

        productList.add(new Product1(1, "C#", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FC%23.pdf?alt=media&token=1edee712-1713-46e9-a47b-5fcb92c7fe81"));

        productList.add(new Product1(1, "DBMS", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FDBMS.pdf?alt=media&token=4abed406-e506-4cb7-bcc9-8e7c695f35c6"));

        productList.add(new Product1(1, "Data Structires", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FData%20Structires.pdf?alt=media&token=da922d78-770c-466f-992f-02aed5666ff1"));

        productList.add(new Product1(1, "Java", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FJava.pdf?alt=media&token=78fd882c-88d5-4233-a666-62357d0d54e5"));

        productList.add(new Product1(1, "Operating-System", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FOperating-System.pdf?alt=media&token=53a7899d-f359-446c-a7f9-9b78533737e2"));

        productList.add(new Product1(1, "Python", 60000, R.drawable.pdf,
                "https://firebasestorage.googleapis.com/v0/b/mydatabase-b4707.appspot.com/o/IT%2FPython.pdf?alt=media&token=2d59dd3d-565c-424c-a996-33da69f28d2a"));



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