package com.example.ishan.wintecapp;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<Model> {

    private final List<Model> list;
    private final Activity context;
    public List<Model> selectedList;

    public InteractiveArrayAdapter(Activity context, List<Model> list) {
        super(context, R.layout.rowbuttonlayout, list);
        this.context = context;
        this.list = list;
        selectedList =new ArrayList<>();

    }

    static class ViewHolder {
        protected TextView text;
        protected TextView title;
        protected TextView descrption;
        protected TextView level;
        protected TextView cridit;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowbuttonlayout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.mCode);
            viewHolder.title = (TextView) view.findViewById(R.id.mTitle);
            viewHolder.descrption = (TextView) view.findViewById(R.id.mDescription);
            viewHolder.level = (TextView) view.findViewById(R.id.txtLevel);
            viewHolder.cridit = (TextView) view.findViewById(R.id.txtCridit);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            Model element = (Model) viewHolder.checkbox
                                    .getTag();
                            element.setSelected(buttonView.isChecked());
                            if(selectedList.size()>0) {
                                boolean found=false;
                                for (int i = 0; i < selectedList.size(); i++) {
                                    if (selectedList.get(i).get_module().get_code() == element.get_module().get_code()) {
                                        selectedList.get(i).setSelected(isChecked);
                                        found=true;
                                    }

                                }
                                if(!found && isChecked){
                                    selectedList.add(element);
                                }

                            }
                            else {
                                selectedList.add(element);
                            }
                        }
                    });
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).get_module().get_code());
        holder.title.setText(list.get(position).get_module().get_title());
        holder.descrption.setText("Description: "+list.get(position).get_module().get_description());
        holder.level.setText("Level: "+list.get(position).get_module().get_level());
        holder.cridit.setText("Credit: 15");

        holder.checkbox.setChecked(list.get(position).isSelected());
        return view;
    }
}
