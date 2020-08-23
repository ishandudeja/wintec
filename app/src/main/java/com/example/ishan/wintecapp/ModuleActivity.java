package com.example.ishan.wintecapp;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class ModuleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));
        tabLayout =(TabLayout) findViewById(R.id.tabLayout_id);
        viewPager=(ViewPager) findViewById(R.id.viewPager_id);

        Bundle appleData = getIntent().getExtras();
        if (appleData== null){
            return;
        }
        Module module = (Module) appleData.getSerializable("moduleData");


        ViewPageAdapter adapter=new ViewPageAdapter(getSupportFragmentManager());

        OverviewFragment overviewFragment=new OverviewFragment();
        overviewFragment.set_module(module);
        adapter.addFragment(overviewFragment,"Overview");
        adapter.addFragment(new TopicFragment(),"Topics");
        adapter.addFragment(new AssessmentsFragment(),"Assessments");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }
}
