package com.example.ishan.wintecapp;

public class Users {
    private int _id;
    private String _name;
    private String _email;
    private String _password;
    private boolean _isAdmin;

    //Added this empty constructor in lesson 50 in case we ever want to create the object and assign it later.
    public Users() {

    }

    public Users(String name, String email, String password, boolean isAdmin) {
        this._name = name;
        this._email = email;
        this._password = password;
        this._isAdmin = isAdmin;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public boolean is_isAdmin() {
        return _isAdmin;
    }

    public void set_isAdmin(boolean _isAdmin) {
        this._isAdmin = _isAdmin;
    }
}
