package com.example.studygramapp;

import java.io.Serializable;

public class Consultation2 implements Serializable {
    private String key; // Add a key field to store the key in Firebase
    private String name;
    private String doubt;
    private String teacherResponse;
    private String studentResponse;

    public Consultation2() {
        // Default constructor required for Firebase
    }

    public Consultation2(String name, String doubt, String teacherResponse, String studentResponse) {
        this.name = name;
        this.doubt = doubt;
        this.teacherResponse = teacherResponse;
        this.studentResponse = studentResponse;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getDoubt() {
        return doubt;
    }

    public String getTeacherResponse() {
        return teacherResponse;
    }

    public void setTeacherResponse(String teacherResponse) {
        this.teacherResponse = teacherResponse;
    }

    public String getStudentResponse() {
        return studentResponse;
    }

    public void setStudentResponse(String studentResponse) {
        this.studentResponse = studentResponse;
    }
}
