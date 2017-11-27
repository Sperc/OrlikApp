package com.example.pawel.orlikapp.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.login.LoginActivity;
import com.example.pawel.orlikapp.ui.main.myteams.MyTeamsFragment;
import com.example.pawel.orlikapp.ui.main.team.TeamFragment;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        drawerLayout.addDrawerListener(toggle);
        navigationView = (NavigationView) findViewById(R.id.navigationDrawer);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigationView);


    }

    @Override
    public void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment fragment=null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.teams:
                fragmentClass = TeamFragment.class;
                break;
            case R.id.logout:
                logout();
                return;
            case R.id.search:
                fragmentClass = FindPlayground.class;
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            default:
                fragmentClass = MyTeamsFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent,fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
    private void logout(){
        PreferencesShared.onDeleteString(PreferencesSharedKyes.token);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
