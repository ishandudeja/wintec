package com.example.ishan.wintecapp;

import java.io.Serializable;

public class Module implements Serializable {
    private int _id;
    private String _code;
    private String _title;
    private int _level;
    private int _credit;
    private String _description;
    private boolean _isCore;
    private Integer  _pathway_id;
    private int _semester;
    public Module() {
    }

    public Module(String code, String title, int level, int credit, String description, boolean isCore, Integer  pathway_id,int semester) {
        this._code = code;
        this._title = title;
        this._level = level;
        this._credit = credit;
        this._description = description;
        this._isCore = isCore;
        this._pathway_id = pathway_id;
        this._semester=semester;

    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_code() {
        return _code;
    }

    public void set_code(String _code) {
        this._code = _code;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public int get_credit() {
        return _credit;
    }

    public void set_credit(int _credit) {
        this._credit = _credit;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public boolean is_isCore() {
        return _isCore;
    }

    public void set_isCore(boolean _isCore) {
        this._isCore = _isCore;
    }



    public Integer get_pathway_id() {
        return _pathway_id;
    }

    public void set_pathway_id(Integer _pathway_id) {
        this._pathway_id = _pathway_id;
    }

    public int get_semester() {
        return _semester;
    }

    public void set_semester(int _semester) {
        this._semester = _semester;
    }
}
