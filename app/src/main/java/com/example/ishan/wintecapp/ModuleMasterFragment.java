package com.example.ishan.wintecapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class ModuleMasterFragment extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener, Serializable {
    ArrayList<Module> mAllValues;
    private ArrayAdapter<Module> mAdapter;
    private Context mContext;

    private OnFragmentInteractionListener mListener;

    public ModuleMasterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        populateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_module_master, container, false);

        View layout = inflater.inflate(R.layout.fragment_module_master, container, false);
        ListView listView = (ListView) layout.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) layout.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);
        return layout;
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        String item = (String) listView.getAdapter().getItem(position);
        if (getActivity() instanceof OnItem1SelectedListener) {
            ((OnItem1SelectedListener) getActivity()).OnItem1SelectedListener(item);
        }
        getFragmentManager().popBackStack();
    }

   
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mListener.setActivityTitle("Manage Module");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        // super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }

        ArrayList<Module> filteredValues = new ArrayList<Module>(mAllValues);
        for (Module value : mAllValues) {
            if (!value.get_code().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

        // mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);

        mAdapter=new ModuleListAdapter(getActivity() , filteredValues);
        setListAdapter(mAdapter);

        return false;
    }

    public void resetSearch() {
        //  mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mAllValues);
        mAdapter= new ModuleListAdapter(getActivity() , mAllValues);

        setListAdapter(mAdapter);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }



    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(String item);
    }

    private void populateList(){

        mAllValues = new ArrayList<>();

        mAllValues.add( new Module( "Afghanistan","Info",7,15,"description",true,1));
        mAllValues.add(new Module( "Åland Islands","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Albania","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Algeria","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "American Samoa","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "AndorrA","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Angola","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Anguilla","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Antarctica","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Antigua and Barbuda","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Argentina","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Armenia","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Aruba","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Australia","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Austria","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Azerbaijan","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bahamas","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bahrain","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bangladesh","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Barbados","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Belarus","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Belgium","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Belize","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Benin","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bermuda","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bhutan","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bolivia","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bosnia and Herzegovina","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Botswana","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Bouvet Island","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "Brazil","Info",7,15,"description",true,1));;
        mAllValues.add(new Module( "British Indian Ocean Territory","Info",7,15,"description",true,1));;
     

        mAdapter = new ModuleListAdapter(getActivity(), mAllValues);
        setListAdapter(mAdapter);
    }


}
