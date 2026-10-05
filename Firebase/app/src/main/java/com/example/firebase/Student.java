package com.example.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

//@IgnoreExtraProperties
public class Student {
    public String id;
    public String name;
    public String course;

    // Required empty constructor for Firebase
    public Student() {}

    public Student(String id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }
}