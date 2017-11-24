package com.example.pawel.orlikapp.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.prefs.SharedPrefs;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
