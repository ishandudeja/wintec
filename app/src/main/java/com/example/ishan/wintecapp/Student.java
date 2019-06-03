package com.example.ishan.wintecapp;

import android.graphics.Bitmap;

import java.util.Date;

public class Student {
    private int _id;
    private  int _student_id;
    private String _name;
    private int _pathway_id;
    private Date _semesterStart;
    private Date _semesterEnd;
    private boolean _isActive;
    private Bitmap _image;
    private int _user_id;

    public Student(){}
    public Student(int student_id,String name,int pathway_id,Date semesterStart,Date semesterEnd,boolean isActive,Bitmap image,int user_id){
        this._student_id=student_id;
        this._name=name;
        this._pathway_id=pathway_id;
        this._semesterEnd=semesterEnd;
        this._semesterStart=semesterStart;
        this._isActive=isActive;
        this._image=image;
        this._user_id=user_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_student_id() {
        return _student_id;
    }

    public void set_student_id(int _student_id) {
        this._student_id = _student_id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_pathway_id() {
        return _pathway_id;
    }

    public void set_pathway_id(int _pathway_id) {
        this._pathway_id = _pathway_id;
    }

    public Date get_semesterStart() {
        return _semesterStart;
    }

    public void set_semesterStart(Date _semesterStart) {
        this._semesterStart = _semesterStart;
    }

    public Date get_semesterEnd() {
        return _semesterEnd;
    }

    public void set_semesterEnd(Date _semesterEnd) {
        this._semesterEnd = _semesterEnd;
    }

    public boolean is_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }

    public Bitmap get_image() {
        return _image;
    }

    public void set_image(Bitmap _image) {
        this._image = _image;
    }

    public int get_user_id() {
        return _user_id;
    }

    public void set_user_id(int _user_id) {
        this._user_id = _user_id;
    }
}
