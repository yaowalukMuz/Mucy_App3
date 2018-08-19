package muz.kumoteamthailand.com.mucyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import muz.kumoteamthailand.com.mucyapp.fragment.FollowFragment;
import muz.kumoteamthailand.com.mucyapp.fragment.PackageSaleFragment;

public class ServiceActivity extends AppCompatActivity {

    private ArrayList<String> stringArrayList = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Load SharePreferance
        loadSharePreferance();

//        Add Fragment
        addFragment(savedInstanceState);


//        Create toolbar

        createToolbar();


//        follow Controller
        followController();

    } // Main Method

    private void followController() {
        TextView textView = findViewById(R.id.txtFollow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain, new FollowFragment())
                        .commit();
                drawerLayout.closeDrawers();
            }
        });
    }


    //<<<<-----Hamberger--->>


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return  true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        actionBarDrawerToggle.syncState();
    }
    //<<<<-----Hamberger--->>
    private void loadSharePreferance() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyLogin", Context.MODE_PRIVATE);
        String loginString = sharedPreferences.getString("Login", "[]");
        Log.d("19AugV2", "LoinString on Service ==>" + loginString);


        loginString = loginString.substring(1,loginString.length()-1);
        String[] strings = loginString.split(",");
        for (int i=0; i<strings.length;i+=1) {
            stringArrayList.add(strings[i].trim());
        }

    }

    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentService, new PackageSaleFragment())
                    .commit();
        }
    }


    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarService);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setSubtitle(stringArrayList.get(1)+ " " + stringArrayList.get(2));

        drawerLayout = findViewById(R.id.layoutDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ServiceActivity.this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_hamberger);

    }


}// Main Class
