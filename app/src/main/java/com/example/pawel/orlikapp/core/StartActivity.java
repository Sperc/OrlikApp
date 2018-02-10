package com.example.pawel.orlikapp.core;

import android.content.Intent;
import android.os.Bundle;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.Main2Activity;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.login.LoginActivity;
import com.example.pawel.orlikapp.ui.menu.main.MainActivity;
import com.example.pawel.orlikapp.ui.select_city.SelectCityActicity;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        executeUserSession();
    }
    public void executeUserSession(){
        String token = PreferencesShared.onReadString(PreferencesSharedKyes.token);
        String city = PreferencesShared.onReadString(PreferencesSharedKyes.city);
        Intent intent;
        if(token.equals("")){
            intent = new Intent(this, LoginActivity.class);
        }else {
            if(!city.equals("")){
                intent = new Intent(this, MainActivity.class);
            }else {
                intent = new Intent(this, SelectCityActicity.class);
            }
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setPresenter() {

    }
}
