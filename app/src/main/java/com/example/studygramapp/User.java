package com.example.studygramapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String name;
    private String addr;
    private String desc;

    public User() {
    }

    public User(String name, String addr, String desc) {
        this.name = name;
        this.addr = addr;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getDesc() {
        return desc;
    }
}