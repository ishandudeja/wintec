package com.example.ishan.wintecapp;

import android.net.Uri;

import java.io.Serializable;

public interface OnFragmentInteractionListener extends Serializable {
    void onFragmentInteraction(Uri uri);
    void setActivityTitle(String title);
}
