package com.example.ishan.wintecapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<Student> implements Serializable {

    public Activity activity;

    public StudentListAdapter(Activity act, ArrayList<Student> students) {

        super(act, R.layout.student_card_list, students);

        activity = act;
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
        TextView sessionStart = customView.findViewById(R.id.semesterStart);
        TextView sessionEnd = customView.findViewById(R.id.semesterEnd);
        Button btnedit = (Button) customView.findViewById(R.id.btnEdit);
        Button btnmap = (Button) customView.findViewById(R.id.btnMap);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.imageView2);

        // dynamically update the text from the array
        itemText.setText(singleTripItem.get_name());
        String pathway = "";
        switch (singleTripItem.get_id()) {
            case 1:
                pathway = "Software Engineer";
                break;
            case 2:
                pathway = "Database Architecture";
                break;
            case 3:
                pathway = "Networking";
                break;
            case 4:
                pathway = "Web Development";
                break;
        }


        sID.setText("Student ID: " + singleTripItem.get_student_id());
        sCourse.setText("Pathway: " + pathway);
        sessionStart.setText("Session - Start: " + singleTripItem.get_semesterStart());
        sessionEnd.setText("End: " + singleTripItem.get_semesterEnd());
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("search", "edit click");
            }
        });
        buckysImage.setImageBitmap(singleTripItem.get_image());

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences studData = activity.getSharedPreferences("studInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = studData.edit();
                editor.putInt("student_id", singleTripItem.get_id());
                editor.putInt("user_id", singleTripItem.get_user_id());
                editor.putInt("pathway", singleTripItem.get_pathway_id());
                editor.apply();

                Intent intent = new Intent(activity, SemesterActivity.class);
                activity.startActivity(intent);

                Log.i("search", "map click");
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
