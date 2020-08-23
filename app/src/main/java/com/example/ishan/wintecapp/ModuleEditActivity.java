package com.example.ishan.wintecapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ModuleEditActivity extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinSem;
    DBHelper db;
    Module module;
    EditText code;
    EditText title;
    EditText description;
    CheckBox iscore;
    Integer pathway = null;
    int sem=1;
    private static final String[] paths = {"Common", "Software Engineer", "Database Architecture", "Networking", "Multi Media Web Development"};
    private static final String[] semesters = {"Semester One", "Semester Two", "Semester Three", "Semester Four", "Semester Five", "Semester Final",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_edit);
        db = new DBHelper(this, null, null, 1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));

        if (getIntent() != null) {
            int module_id = getIntent().getIntExtra("module_id", 0);

            module = db.getModuleById(module_id);
        }

        code = findViewById(R.id.editTextCode);
        title = findViewById(R.id.editTextTitle);
        description = findViewById(R.id.editTextDescription);

        iscore = findViewById(R.id.chkIsCore);

        code.setText(module.get_code());
        title.setText(module.get_title());
        description.setText(module.get_description());
        iscore.setChecked(module.is_isCore());

        spinner = (Spinner) findViewById(R.id.spin_pathway);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //int spinnerPosition = adapter.getPosition(compareValue);
        int _pathway=0;
        if (module.get_pathway_id() != null) {
            _pathway = module.get_pathway_id();
        }
        spinner.setSelection(_pathway);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(id==0){
                    pathway=null;
                }
               else
                pathway=(int)id;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinSem = (Spinner) findViewById(R.id.spin_sem);
        ArrayAdapter<String> adapterspinSem = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, semesters);

        adapterspinSem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSem.setAdapter(adapterspinSem);
        spinSem.setSelection(module.get_semester() - 1);
        spinSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sem=(int)id+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinSem.setSelection(module.get_semester() - 1);
            }
        });
    }

    public  void saveModule(View v){
        module.set_code(code.getText().toString());
        module.set_title(title.getText().toString());
        module.set_description(description.getText().toString());
        module.set_pathway_id(pathway);
        module.set_semester(sem);
        module.set_isCore(iscore.isChecked());
      long result=  db.updateModule(module);
        if(result>0){
            Intent intent =new Intent(ModuleEditActivity.this,TaskMasterActivity.class);
            startActivity(intent);
            Toast.makeText(ModuleEditActivity.this, "Save Successfully",
                    Toast.LENGTH_SHORT).show();

        }
    }

}
