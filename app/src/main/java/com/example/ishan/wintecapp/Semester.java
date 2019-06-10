package com.example.ishan.wintecapp;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    public String semesterName;
    public final List<Module> modules = new ArrayList<Module>();

    public Semester(String semister) {
        this.semesterName = semister;
    }
}
