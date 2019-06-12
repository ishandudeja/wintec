package com.example.ishan.wintecapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SemesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));
    }

    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.sem1:
                Toast.makeText(this, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sem2:
                Toast.makeText(this, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sem3:
                Toast.makeText(this, "Button 3 Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sem4:
                Toast.makeText(this, "Button 4 Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sem5:
                Toast.makeText(this, "Button 5 Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sem6:
                Toast.makeText(this, "Button 5 Clicked", Toast.LENGTH_SHORT).show();
                break;
        }


        Intent intent= new Intent(SemesterActivity.this,ModuleMaperActivity.class);
        startActivity(intent);


    }
}
