package com.example.studygramapp;

public class DataClass {
    private String imageUrl;
    private String uploadBtn;

    public DataClass(String imageUrl, String experience) {
        this.imageUrl = imageUrl;
        this.uploadBtn = uploadBtn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExperience() {
        return uploadBtn;
    }

    public void setExperience(String experience) {
        this.uploadBtn = experience;

    }
}
