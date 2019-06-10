package com.example.ishan.wintecapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<Student> {

    public Activity activity;

    public StudentListAdapter(Context act, ArrayList<Student> students) {

        super(act, R.layout.student_card_list, students);

       // activity = act;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // default -  return super.getView(position, convertView, parent);
        // add the layout
        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.student_card_list, parent, false);
        // get references.
        final Student singleTripItem = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.sName);
        TextView sID = (TextView) customView.findViewById(R.id.sID);
        TextView sCourse = (TextView) customView.findViewById(R.id.sCourse);
        Button btnedit=(Button) customView.findViewById(R.id.btnEdit);
        Button btnmap=(Button) customView.findViewById(R.id.btnMap);
        // ImageView buckysImage = (ImageView) customView.findViewById(R.id.imageView);

        // dynamically update the text from the array
        itemText.setText(singleTripItem.get_name());
        sID.setText("Student ID: " + singleTripItem.get_id());
        sCourse.setText("Course: " + singleTripItem.get_pathway_id());

        btnedit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("search" ,"edit click");
            }
        });


        btnmap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("search" ,"map click");
            }
        });






        // using the same image every time
        //buckysImage.setImageResource(R.drawable.farrri);
        // Now we can finally return our custom View or custom item

//        convertView.findViewById(R.id.studentCard).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Log.i("search",singleTripItem.get_name());
//////                Intent intent=new Intent(activity,ModuleActivity.class);
//////                activity.startActivity(intent);
//////                Toast.makeText(activity, singleTripItem.get_name(),
////////                        Toast.LENGTH_SHORT).show();
////            }
////        });
        return customView;
    }
}
