package com.example.ishan.wintecapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.os.CountDownTimer;
import android.support.v7.widget.CardView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridLayout mainGrid;
    DBHelper db;
    boolean _isAdmin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.winteclogo);
        setSupportActionBar(toolbar);
        db = new DBHelper(this, null, null, 1);
        db.insertPathway();
        db.dbUserInsert();
        db.insertModule();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);

        SharedPreferences declaration = getSharedPreferences("declarationInfo", Context.MODE_PRIVATE);
        boolean accepted = declaration.getBoolean("accepted",false);
        if(!accepted) {
            CountDownTimer event = new CountDownTimer(30000, 10) {

                public void onTick(long millisUntilFinished) {
                    // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {
                    Intent intent = new Intent(MainActivity.this, DeclarationActivity.class);
                    startActivity(intent);
                }

            }.start();
        }

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        boolean isAdmin = loginData.getBoolean("isAdmin", false);
        int user_id = loginData.getInt("user_id", 0);
        if (!isAdmin && user_id > 0) {
            Student stud = db.getStudentByUser_Id(user_id);
            CardView se=findViewById(R.id.card_se);
            CardView dbe=findViewById(R.id.card_db);
            CardView ne=findViewById(R.id.card_ne);
            CardView wd=findViewById(R.id.card_wd);

            switch (stud.get_pathway_id())
            {
                case 1:
                    dbe.setVisibility(View.GONE);
                    ne.setVisibility(View.GONE);
                    wd.setVisibility(View.GONE);
                    break;
                case 2:
                    se.setVisibility(View.GONE);
                    ne.setVisibility(View.GONE);
                    wd.setVisibility(View.GONE);
                    break;
                case 3:
                    ne.setVisibility(View.GONE);
                    se.setVisibility(View.GONE);
                    wd.setVisibility(View.GONE);
                    break;
                case 4:
                    se.setVisibility(View.GONE);
                    dbe.setVisibility(View.GONE);
                    ne.setVisibility(View.GONE);
                    break;
            }


        }
        else if(isAdmin && user_id > 0){
            _isAdmin=true;
        }



    }



//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this, AboutAtivity.class);
            startActivity(i);

        } else if (id == R.id.nav_declaration) {
            Intent i = new Intent(MainActivity.this, DeclarationActivity.class);
            startActivity(i);
        }

//        else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        }

        else if (id == R.id.nav_signIn) {

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_admin) {
            if(_isAdmin) {
                Intent i = new Intent(MainActivity.this, TaskMasterActivity.class);
                startActivity(i);
            }
            else {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Please Sign In as Admin", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, ModuleListActivity.class);
                    intent.putExtra("info", finalI);
                    startActivity(intent);

                }
            });
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(MainActivity.this,AboutAtivity.class);
//        setIntent.addCategory(Intent.CATEGORY_HOME);
//        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
