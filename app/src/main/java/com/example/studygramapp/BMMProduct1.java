package com.example.studygramapp;

public class BMMProduct1 {
    private int id;
    private String title;
    private int price;
    private int image;
    private String pdfUrl;

    public BMMProduct1(int id, String title, int price, int image, String pdfUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.pdfUrl = pdfUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }
}
