package com.example.ishan.wintecapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Student implements Serializable {
    private int _id;
    private int _student_id;
    private String _name;
    private int _pathway_id;
    private String _semesterStart;
    private String _semesterEnd;
    private boolean _isActive;
    private byte[] _image;
    private int _user_id;



    public Student(int student_id, String name, int pathway_id, String semesterStart, String semesterEnd, boolean isActive, Bitmap bitmap, int user_id) {
        this._student_id = student_id;
        this._name = name;
        this._pathway_id = pathway_id;
        this._semesterEnd = semesterEnd;

        this._semesterStart = semesterStart;
        this._isActive = isActive;
        if(bitmap!=null)
        { ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        this._image = stream.toByteArray();}
        this._user_id = user_id;
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

    public String get_semesterStart() {
        return _semesterStart;
    }

    public void set_semesterStart(String _semesterStart) {
        this._semesterStart = _semesterStart;
    }

    public String get_semesterEnd() {
        return _semesterEnd;
    }

    public void set_semesterEnd(String _semesterEnd) {
        this._semesterEnd = _semesterEnd;
    }

    public boolean is_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }

    public Bitmap get_image() {
        // return _image;
        return BitmapFactory.decodeByteArray(_image, 0, _image.length);

    }

    public byte[] get_Byte_image() {
        // return _image;
        // return BitmapFactory.decodeByteArray(_image, 0, _image.length);
        return _image;
    }

    public void set_image(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);

        this._image = stream.toByteArray();
    }
    public void set_Byte_image(byte[] bitmap) {

        this._image = bitmap;
    }


    public int get_user_id() {
        return _user_id;
    }

    public void set_user_id(int _user_id) {
        this._user_id = _user_id;
    }
}
