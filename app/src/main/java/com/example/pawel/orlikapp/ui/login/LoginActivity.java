package com.example.pawel.orlikapp.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.prefs.SharedPrefs;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.find_orlik.FindOrlikActicity;
import com.example.pawel.orlikapp.ui.find_orlik.FindOrlikActivityInterface;
import com.example.pawel.orlikapp.ui.main.MainActivity;

import java.util.Optional;

public class LoginActivity extends BaseActivity implements LoginActiivityInterface, LoginPresenter.LoginPresenterListener {


    private SharedPrefs sharedPrefs;
    private EditText username;
    private EditText password;
    private Button logIn;
    private Button register;
    private TextView forgotPassword;
    private CheckBox checkBox;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setPresenter();
        initialize();
        onButtonClick();
        //onButtonClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!sharedPrefs.readString("login").equals("")) {
            username.setText(sharedPrefs.readString("login"));
            checkBox.setChecked(true);
        }
    }

    @Override
    public void initialize() {
        sharedPrefs = new SharedPrefs(this);
        checkBox = (CheckBox) findViewById(R.id.saveUsername);
        logIn = (Button) findViewById(R.id.logIn);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

    }

    @Override
    public void setPresenter() {
        loginPresenter = new LoginPresenter(this, this);
    }


    @Override
    public void onButtonClick() {
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    sharedPrefs.storeLogin(username.getText().toString());
                } else {
                    sharedPrefs.deleteString("login");
                }
                loginPresenter.login(username.getText().toString(), password.getText().toString());
            }
        });

    }

    @Override
    public void loginSucces(AppUser appUser) {
        //startNewActivity(this, MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, R.string.invalidCredentialis, Toast.LENGTH_LONG).show();
    }
}
