package com.example.ishan.wintecapp;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

public class TaskMasterActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private TextView mTextMessage;
    Toolbar myToolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new ModuleMapFragment();
                    break;
                case R.id.navigation_module:

                    fragment = new ModuleMasterFragment();
                    break;
                case R.id.navigation_add_student:

                    fragment = new StudentMasterFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_master);

         myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bg1));
        loadFragment(new ModuleMapFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment

        String TAG="NOTAG";
        if (fragment != null) {
           if(fragment instanceof ModuleMapFragment){
               TAG="ModuleMapFragment";
           }else if(fragment instanceof StudentMasterFragment)
           {
               TAG="StudentMasterFragment";
           }else if(fragment instanceof ModuleMasterFragment)
           {
               TAG="ModuleMasterFragment";
           }

           Log.i("search",TAG);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment,TAG)
                    .addToBackStack(TAG)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            return true;
        }
        return false;
    }

    @Override
    public void setActivityTitle(String title) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(TaskMasterActivity.this,MainActivity.class);
//        setIntent.addCategory(Intent.CATEGORY_HOME);
//        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



//    @Override
//    protected void onNewIntent(Intent intent) {
//
//        handleIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//           // use the query to search your data somehow
//           getSupportFragmentManager().executePendingTransactions();
//
//
//            List<Fragment> fragments = getSupportFragmentManager().getFragments();
//            if(fragments != null){
//                for(Fragment fragment : fragments){
//                    if(fragment != null && fragment instanceof ModuleMapFragment)
//                        Log.d("search", "Fragment returned is valid.");
//                }
//            }
//
////            ModuleMapFragment moduleMapFragment = (ModuleMapFragment)getSupportFragmentManager().findFragmentByTag("ModuleMapFragment");
////            if (moduleMapFragment != null) {
////               // moduleMapFragment.passBundleToFragment(dataBundle);
////                Log.d("search", "Fragment returned is valid.");
////            } else {
////                Log.d("search", "Fragment returned is null.");
////            }
//
//          // moduleMapFragment.filterList(query);
//            Log.i("search",query);
//        }
//    }

}
