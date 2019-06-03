package com.example.ishan.wintecapp;

public class Requisites {

    private  int _id;
    private int _module_id;
    private int _requisite_id;
    private boolean _isCoRequisite;
    private boolean _isActive;

    public Requisites(){}
    public Requisites(int _module_id,int _requisite_id,boolean _isCoRequisite,boolean _isActive){
        this._module_id=_module_id;
        this._requisite_id=_requisite_id;
        this._isCoRequisite=_isCoRequisite;
        this._isActive=_isActive;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_module_id() {
        return _module_id;
    }

    public void set_module_id(int _module_id) {
        this._module_id = _module_id;
    }

    public int get_requisite_id() {
        return _requisite_id;
    }

    public void set_requisite_id(int _requisite_id) {
        this._requisite_id = _requisite_id;
    }

    public boolean is_isCoRequisite() {
        return _isCoRequisite;
    }

    public void set_isCoRequisite(boolean _isCoRequisite) {
        this._isCoRequisite = _isCoRequisite;
    }

    public boolean is_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }
}
