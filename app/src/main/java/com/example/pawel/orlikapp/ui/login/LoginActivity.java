package com.example.pawel.orlikapp.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginActiivityInterface {
    private EditText username;
    private EditText password;
    private Button login;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText4);
        login = (Button) findViewById(R.id.button7);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.onLoad(this);
        loginPresenter.login("psosnowka","haslo");
    }

    @Override
    public void onAction(AppUser appUser) {
        backgroundThreadShortToast(this,appUser.getUsername());
        String w = appUser.getFirstName();
    }

    @Override
    public void login(String username, String password) {

    }
}
