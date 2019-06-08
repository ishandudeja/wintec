package com.example.ishan.wintecapp;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Semester> groups;
    public LayoutInflater inflater;
    public Activity activity;

    public ExpandableListAdapter(Activity act, SparseArray<Semester> groups) {
        activity = act;
        this.groups = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).modules.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Module children = (Module) getChild(groupPosition, childPosition);
        TextView code = null;
        TextView title=null;
        TextView description=null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.module_card_list, null);
        }
        code = (TextView) convertView.findViewById(R.id.mCode);
        code.setText(children.get_code());
         title=convertView.findViewById(R.id.mTitle);
         title.setText(children.get_title());

         description=convertView.findViewById(R.id.mDescription);
         description.setText(children.get_description());

        convertView.findViewById(R.id.moduleCard).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,ModuleActivity.class);
                 activity.startActivity(intent);
                Toast.makeText(activity, children.get_title(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).modules.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_semester, null);
        }
        Semester group = (Semester) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.semesterName);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}



