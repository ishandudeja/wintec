package com.example.ishan.wintecapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeclarationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));

        TextView declaration = findViewById(R.id.textDeclaration);
        declaration.setText("The DPM App is a Prototype version of a degree planner only and NOT an official study plan. It is intended that students" +
                "shall use this to get an understanding of the pathways offered and then discuss study plans with a Wintec representative.\n" +
                "\nPathways may change accordingly making older versions redundant – Wintec takes no responsibility of outdated pathways" +
                "or information displayed on the app.");
        SharedPreferences loginData = getSharedPreferences("declarationInfo", Context.MODE_PRIVATE);
        boolean accepted = loginData.getBoolean("accepted", false);
        if (accepted) {
            TextView txtaccepted = findViewById(R.id.textAccepted);
            txtaccepted.setVisibility(View.VISIBLE);
        }
    }

    public void accepted(View v) {

        Intent intent = new Intent(DeclarationActivity.this, MainActivity.class);
        startActivity(intent);

        SharedPreferences loginData = getSharedPreferences("declarationInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putBoolean("accepted", true);
        editor.apply();

    }
}
