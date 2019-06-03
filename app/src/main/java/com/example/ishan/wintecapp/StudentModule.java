package com.example.ishan.wintecapp;

public class StudentModule {
    private int _id;
    private int _student_id;
    private int _module_id;
    private String _semester;
    private int _completionRate;
    private boolean _isCompete;
    private String _grade;
    private String _result;
    private boolean _isActive;

    public StudentModule() {
    }

    public StudentModule(int _student_id, int _module_id, String _semester, int _completionRate, boolean _isCompete, String _grade, String _result, boolean _isActive) {
        this._student_id = _student_id;
        this._module_id = _module_id;
        this._semester = _semester;
        this._completionRate = _completionRate;
        this._isCompete = _isCompete;
        this._grade = _grade;
        this._result = _result;
        this._isActive = _isActive;
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

    public int get_module_id() {
        return _module_id;
    }

    public void set_module_id(int _module_id) {
        this._module_id = _module_id;
    }

    public String get_semester() {
        return _semester;
    }

    public void set_semester(String _semester) {
        this._semester = _semester;
    }

    public int get_completionRate() {
        return _completionRate;
    }

    public void set_completionRate(int _completionRate) {
        this._completionRate = _completionRate;
    }

    public boolean is_isCompete() {
        return _isCompete;
    }

    public void set_isCompete(boolean _isCompete) {
        this._isCompete = _isCompete;
    }

    public String get_grade() {
        return _grade;
    }

    public void set_grade(String _grade) {
        this._grade = _grade;
    }

    public String get_result() {
        return _result;
    }

    public void set_result(String _result) {
        this._result = _result;
    }

    public boolean is_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }
}
