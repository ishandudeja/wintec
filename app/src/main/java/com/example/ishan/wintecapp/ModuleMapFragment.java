package com.example.ishan.wintecapp;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;




public class ModuleMapFragment extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener , Serializable, Parcelable {

    ArrayList<Student> mAllValues;
    private ArrayAdapter<Student> mAdapter;
    private Context mContext;
    Drawable buckysFace;
    Bitmap bitmapImage;
    DBHelper db;

    private OnFragmentInteractionListener mListener;

    public ModuleMapFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        db = new DBHelper(getActivity(), null, null, 1);
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
       // populateList();
        mAllValues=db.getStudents();
        mAdapter = new StudentListAdapter(getActivity(), mAllValues);
        setListAdapter(mAdapter);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_module_map, container, false);

        View layout = inflater.inflate(R.layout.fragment_module_map, container, false);
        ListView listView = (ListView) layout.findViewById(android.R.id.list);
        TextView emptyTextView = (TextView) layout.findViewById(android.R.id.empty);
        listView.setEmptyView(emptyTextView);
        return layout;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mListener.setActivityTitle("Module Maper");
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

        ArrayList<Student> filteredValues = new ArrayList<Student>(mAllValues);
        for (Student value : mAllValues) {
            if (!value.get_name().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

       // mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);

        mAdapter=new StudentListAdapter(getActivity() , filteredValues);
        setListAdapter(mAdapter);

        return false;
    }

    public void resetSearch() {
      //  mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mAllValues);
        mAdapter= new StudentListAdapter(getActivity() , mAllValues);

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(String item);
    }

    private void populateList(){

        mAllValues = new ArrayList<>();
        buckysFace=  getResources().getDrawable(R.drawable.app_logo1);//.getDrawable(R.drawable.image);
        bitmapImage=((BitmapDrawable)buckysFace).getBitmap();
        


        mAllValues.add( new Student( 1,"Afghanistan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Åland Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Albania",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Algeria",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"American Samoa",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"AndorrA",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Angola",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Anguilla",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Antarctica",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Antigua and Barbuda",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Argentina",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Armenia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Aruba",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Australia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Austria",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Azerbaijan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bahamas",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bahrain",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bangladesh",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Barbados",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Belarus",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Belgium",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Belize",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Benin",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bermuda",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bhutan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bolivia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bosnia and Herzegovina",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Botswana",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bouvet Island",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Brazil",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"British Indian Ocean Territory",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Brunei Darussalam",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Bulgaria",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Burkina Faso",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Burundi",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cambodia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cameroon",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Canada",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cape Verde",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cayman Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Central African Republic",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Chad",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Chile",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"China",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Christmas Island",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cocos (Keeling) Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Colombia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Comoros",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Congo",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Congo, The Democratic Republic of the",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cook Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Costa Rica",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cote D\'Ivoire",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Croatia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cuba",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Cyprus",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Czech Republic",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Denmark",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Djibouti",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Dominica",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Dominican Republic",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Ecuador",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Egypt",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"El Salvador",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Equatorial Guinea",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Eritrea",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Estonia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Ethiopia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Falkland Islands (Malvinas)",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Faroe Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Fiji",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Finland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"France",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"French Guiana",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"French Polynesia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"French Southern Territories",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Gabon",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Gambia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Georgia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Germany",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Ghana",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Gibraltar",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Greece",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Greenland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Grenada",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guadeloupe",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guam",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guatemala",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guernsey",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guinea",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guinea-Bissau",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Guyana",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Haiti",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Heard Island and Mcdonald Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Holy See (Vatican City State)",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Honduras",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Hong Kong",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Hungary",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Iceland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"India",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Indonesia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Iran, Islamic Republic Of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Iraq",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Ireland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Isle of Man",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Israel",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Italy",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Jamaica",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Japan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Jersey",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Jordan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Kazakhstan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Kenya",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Kiribati",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Korea, Democratic People\'S Republic of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Korea, Republic of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Kuwait",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Kyrgyzstan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Lao People\'S Democratic Republic",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Latvia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Lebanon",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Lesotho",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Liberia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Libyan Arab Jamahiriya",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Liechtenstein",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Lithuania",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Luxembourg",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Macao",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Macedonia, The Former Yugoslav Republic of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Madagascar",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Malawi",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Malaysia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Maldives",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mali",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Malta",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Marshall Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Martinique",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mauritania",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mauritius",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mayotte",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mexico",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Micronesia, Federated States of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Moldova, Republic of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Monaco",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mongolia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Montserrat",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Morocco",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Mozambique",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Myanmar",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Namibia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Nauru",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Nepal",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Netherlands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Netherlands Antilles",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"New Caledonia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"New Zealand",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Nicaragua",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Niger",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Nigeria",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Niue",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Norfolk Island",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Northern Mariana Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Norway",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Oman",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Pakistan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Palau",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Palestinian Territory, Occupied",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Panama",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Papua New Guinea",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Paraguay",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Peru",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Philippines",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Pitcairn",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Poland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Portugal",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Puerto Rico",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Qatar",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Reunion",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Romania",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Russian Federation",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"RWANDA",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Saint Helena",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Saint Kitts and Nevis",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Saint Lucia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Saint Pierre and Miquelon",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Saint Vincent and the Grenadines",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Samoa",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"San Marino",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Sao Tome and Principe",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Saudi Arabia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Senegal",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Serbia and Montenegro",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Seychelles",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Sierra Leone",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Singapore",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Slovakia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Slovenia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Solomon Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Somalia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"South Africa",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"South Georgia and the South Sandwich Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Spain",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Sri Lanka",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Sudan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Suriname",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Svalbard and Jan Mayen",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Swaziland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Sweden",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Switzerland",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Syrian Arab Republic",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Taiwan, Province of China",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Tajikistan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Tanzania, United Republic of",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Thailand",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Timor-Leste",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Togo",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Tokelau",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Tonga",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Trinidad and Tobago",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Tunisia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Turkey",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Turkmenistan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Turks and Caicos Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Tuvalu",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Uganda",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Ukraine",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"United Arab Emirates",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"United Kingdom",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"United States",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"United States Minor Outlying Islands",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Uruguay",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Uzbekistan",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Vanuatu",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Venezuela",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Viet Nam",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Virgin Islands, British",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Virgin Islands, U.S.",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Wallis and Futuna",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Western Sahara",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Yemen",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Zambia",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));
        mAllValues.add(new Student( 1,"Zimbabwe",1,new Date().toString(),new Date().toString(),true,bitmapImage,1));


        mAdapter = new StudentListAdapter(getActivity(), mAllValues);
        setListAdapter(mAdapter);
    }

}
