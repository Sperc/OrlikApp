package com.example.pawel.orlikapp.core;

import android.content.Intent;
import android.os.Bundle;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.login.LoginActivity;
import com.example.pawel.orlikapp.ui.menu.main.MainActivity;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        executeUserSession();

    }
    public void executeUserSession(){
        String token = PreferencesShared.onReadString(PreferencesSharedKyes.token);
        if(token.equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
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
