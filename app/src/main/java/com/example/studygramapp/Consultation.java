package com.example.studygramapp;

public class Consultation {
    private String name;
    private String doubt;
    private String date;
    private String time;
    private String teacherEmail; // Added teacherEmail field

    public Consultation() {
        // Default constructor required for Firebase
    }

    public Consultation(String name, String doubt, String date, String time, String teacherEmail ) {
        this.name = name;
        this.doubt = doubt;
        this.date = date;
        this.time = time;
        this.teacherEmail = teacherEmail;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDoubt() {
        return doubt;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }


}
