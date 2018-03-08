package com.example.pawel.orlikapp.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.create_player.CreatePlayerActivity;
import com.example.pawel.orlikapp.ui.menu.main.MainActivity;
import com.example.pawel.orlikapp.ui.registration.RegistrationActivity;
import com.example.pawel.orlikapp.ui.select_city.SelectCityActicity;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity implements LoginView, LoginPresenter.LoginPresenterListener {


    private EditText username;
    private EditText password;
    private Button logIn;
    //    private Button register;
    private TextView forgotPassword;
    private TextView signIn;
    private CheckBox checkBox;
    private LoginPresenter loginPresenter;
//    private SharedPrefs sharedPrefs;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setPresenter();
        initialize();
        onButtonClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //tutaj zmieniane prefsy
        if (!PreferencesShared.onReadString(PreferencesSharedKyes.login).equals("")) {
//            username.setText(sharedPrefs.onReadString("login"));
            username.setText(PreferencesShared.onReadString(PreferencesSharedKyes.login));
            checkBox.setChecked(true);
        }
    }

    @Override
    public void initialize() {
        //sharedPrefs = new SharedPrefs(this);
        signIn = (TextView) findViewById(R.id.signInTextView);
        checkBox = (CheckBox) findViewById(R.id.saveUsername);
        logIn = (Button) findViewById(R.id.logIn);
//        register = (Button) findViewById(R.id.registerButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void setPresenter() {
        loginPresenter = new LoginPresenter(this, this, this);
    }


    @Override
    public void onButtonClick() {
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    PreferencesShared.onStoreData(PreferencesSharedKyes.login, username.getText().toString());
                } else {
                    PreferencesShared.onDeleteString(PreferencesSharedKyes.login);
                }
                progressBar.setVisibility(View.VISIBLE);
                loginPresenter.onLogin(username.getText().toString(), password.getText().toString());
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void loginSucces() {
//        Intent intent = new Intent(this, SelectCityActicity.class);
//        startActivity(intent);
//        finish();
        loginPresenter.getActualPlayer(new LoginPresenter.ActualPlayerListener() {
            @Override
            public void succes(Player player) {
                Intent intent = new Intent(getApplicationContext(), SelectCityActicity.class);
                startActivity(intent);
                progressBar.setVisibility(View.INVISIBLE);
                finish();

            }

            @Override
            public void notFound() {
                Intent intent = new Intent(getApplicationContext(), CreatePlayerActivity.class);
                intent.putExtra("token", PreferencesShared.onReadString(PreferencesSharedKyes.token));
                PreferencesShared.onDeleteString(PreferencesSharedKyes.token);
                startActivity(intent);
                progressBar.setVisibility(View.INVISIBLE);
                finish();
            }
        });
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, R.string.invalidCredentialis, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onServerError() {
        Toast.makeText(this, getString(R.string.connectionError), Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.emptyBox));
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.emptyBox));
        progressBar.setVisibility(View.INVISIBLE);
    }
}
