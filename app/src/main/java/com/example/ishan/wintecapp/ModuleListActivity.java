package com.example.ishan.wintecapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.util.SparseArray;

import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ModuleListActivity extends AppCompatActivity {
    SparseArray<Semester> semesters = new SparseArray<Semester>();
    ArrayList<Module> modules;
    ArrayList<StudentModule> studentModules;
    DBHelper db;
boolean isStudent=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_list);
        TextView txtInfo = (TextView) findViewById(R.id.txtInfo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));
        db = new DBHelper(this, null, null, 1);
        if (getIntent() != null) {
            int info = getIntent().getIntExtra("info", 1);
            Log.i("search", String.valueOf(info));

            modules = db.getModuleByPathway(info + 1);

            switch (info) {
                case 0:
                    txtInfo.setText("Software Engineer");
                    break;
                case 1:
                    txtInfo.setText("Database Architecture");
                    break;
                case 2:
                    txtInfo.setText("Networking");
                    break;
                case 3:
                    txtInfo.setText("Multi Media Web Development");
                    break;


            }


        }
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        boolean isAdmin = loginData.getBoolean("isAdmin", false);
        int user_id = loginData.getInt("user_id", 0);
        if (!isAdmin && user_id > 0) {
            Student stud = db.getStudentByUser_Id(user_id);

          studentModules=   db.getStudentModuleByStud_Id(stud.get_id());
          isStudent=true;
        }


        createData();

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.moduleListView);
        final ExpandableListAdapter adapter = new ExpandableListAdapter(this,
                semesters);
        listView.setAdapter(adapter);

//      CardView cw =(CardView) listView.findViewById(R.id.moduleCard);
//              cw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(ModuleListActivity.this,ModuleActivity.class);
//                 startActivity(intent);
// TextView tx= (TextView) v.findViewById(R.id.mCode);
//
//                Toast.makeText(ModuleListActivity.this, tx.getText().toString(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void createData() {

        for (int j = 1; j < 7; j++) {
            String txtsem = "";
            switch (j) {
                case 1:
                    txtsem = "One";
                    break;
                case 2:
                    txtsem = "Two";
                    break;
                case 3:
                    txtsem = "Three";
                    break;
                case 4:
                    txtsem = "Four";
                    break;
                case 5:
                    txtsem = "Five";
                    break;
                case 6:
                    txtsem = "Final";
                    break;
            }

            Semester sem = new Semester("Semester " + txtsem);

            for (int i = 0; i < modules.size(); i++) {
                if (modules.get(i).get_semester() == j) {
                    if(studentModules!=null && studentModules.size()>0){
                        for (int e=0;e<studentModules.size();e++){
                            if(modules.get(i).get_id()==studentModules.get(e).get_module_id())
                            {
                                sem.modules.add(modules.get(i));
                            }
                        }
                    }
                    else if(!isStudent)
                    { sem.modules.add(modules.get(i));}
                }
                //   Module m=new Module("Info-"+i,"Module Title"+i,7,15,"description",true, 1,1);

                // sem.modules.add(m);
            }
            semesters.append(j - 1, sem);
        }
    }
}
