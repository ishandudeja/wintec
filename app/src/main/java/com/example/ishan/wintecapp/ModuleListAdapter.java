package com.example.ishan.wintecapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ModuleListAdapter extends ArrayAdapter<Module> implements Serializable {
    public Activity activity;
    public ModuleListAdapter(Activity context, ArrayList<Module> modules) {
        super(context, R.layout.module_card_list, modules);

        activity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // default -  return super.getView(position, convertView, parent);
        // add the layout
        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.module_card_list, parent, false);
        // get references.
        final Module singleItem = getItem(position);
        TextView mCode = (TextView) customView.findViewById(R.id.mCode);
        TextView mTitle = (TextView) customView.findViewById(R.id.mTitle);
        TextView mDescription = (TextView) customView.findViewById(R.id.mDescription);
        CardView cardView=(CardView) customView.findViewById(R.id.moduleCard) ;
        // ImageView buckysImage = (ImageView) customView.findViewById(R.id.imageView);

        // dynamically update the text from the array
        mCode.setText(singleItem.get_code());
        mTitle.setText("Title: " + singleItem.get_title());
        mDescription.setText("Description: " + singleItem.get_description());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, singleItem.get_code(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        // Now we can finally return our custom View or custom item
        return customView;
    }
}
