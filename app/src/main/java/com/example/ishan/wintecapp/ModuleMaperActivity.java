package com.example.ishan.wintecapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleMaperActivity extends AppCompatActivity {
    Toolbar myToolbar;
    DBHelper db;
    ArrayList<Module> modules;
    ListAdapter customListAdapter;
    String semester;
    ArrayList<StudentModule> studentModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_maper);
        db = new DBHelper(this, null, null, 1);
        myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));


        Intent intent = getIntent();
        handleIntent(intent);
        int sem = 1;
        if (getIntent() != null) {
            semester = getIntent().getStringExtra("semester");

            switch (semester) {
                case "Semester One":
                    sem = 1;
                    break;
                case "Semester Two":
                    sem = 2;
                    break;
                case "Semester Three":
                    sem = 3;
                    break;
                case "Semester Four":
                    sem = 4;
                    break;
                case "Semester Five":
                    sem = 5;
                    break;
                case "Semester Six":
                    sem = 6;
                    break;
            }


            actionBar.setTitle(semester);
        }

        SharedPreferences loginData = getSharedPreferences("studInfo", Context.MODE_PRIVATE);
        int pathway = loginData.getInt("pathway", 0);
        int student_id = loginData.getInt("student_id", 0);
        Log.i("search", "pthway:" + pathway + " sem:" + sem);
        modules = db.getModuleByPathway_sem(pathway, sem);
        studentModules = db.getStudentModuleByStud_Id(student_id);
        customListAdapter = new InteractiveArrayAdapter(this,
                getModel());
        ListView customListView = (ListView) findViewById(R.id.moduleList);
        customListView.setAdapter(customListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            List<Model> list = ((InteractiveArrayAdapter) customListAdapter).selectedList;
            SharedPreferences loginData = getSharedPreferences("studInfo", Context.MODE_PRIVATE);
            int student_id = loginData.getInt("student_id", 0);
            long result = 0;
            for (int i = 0; i < list.size(); i++) {

                Module module = list.get(i).get_module();

                StudentModule studentModule = new StudentModule(student_id, module.get_id(), semester, 0, false, "In Progress", "In Progress", list.get(i).isSelected());

                int _id = db.isStudMapExist(studentModule);

                if (_id > 0) {
                    result = db.updateStudentModule(studentModule, _id);
                } else {
                    result = db.createStudentModule(studentModule);
                }


            }

            if (result > 0) {
                Intent intent = new Intent(ModuleMaperActivity.this, SemesterActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Save Successfully",
                        Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_core_module) {
            return true;
        }
        if (id == R.id.action_pre_request) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // use the query to search your data somehow


            Log.i("search", query);
        }
    }

    private List<Model> getModel() {
        List<Model> list = new ArrayList<Model>();

        for (int i = 0; i < modules.size(); i++) {

            Model m = get(modules.get(i));
            for (int j = 0; j < studentModules.size(); j++) {
                if (modules.get(i).get_id() == studentModules.get(j).get_module_id()) {

                            m.setSelected(studentModules.get(j).is_isActive());

                }

            }


            list.add(m);

        }
        //Log.i("search", "isCore" + modules.get(1).is_isCore());
        // Initially select one of the items

        return list;
    }

    private Model get(Module module) {
        return new Model(module);
    }

}
