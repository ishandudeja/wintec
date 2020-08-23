package com.example.ishan.wintecapp;

public class Model {
    private Module _module;
    private boolean selected;

    public Model(Module module) {
        this._module = module;
        selected = module.is_isCore();
    }

    public Module get_module() {
        return this._module;
    }

    public void set_module(Module module) {
        this._module = module;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
