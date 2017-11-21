package com.example.pawel.orlikapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.SharedPrefs;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.main.MainActivity;
import com.example.pawel.orlikapp.ui.my_team.MyTeamsActivity;
import com.example.pawel.orlikapp.ui.registration.RegistrationActivity;
import com.example.pawel.orlikapp.ui.team.TeamActivity;

public class LoginActivity extends BaseActivity implements LoginView, LoginPresenter.LoginPresenterListener {


    private EditText username;
    private EditText password;
    private Button logIn;
    private Button register;
    private TextView forgotPassword;
    private CheckBox checkBox;
    private LoginPresenter loginPresenter;
    private SharedPrefs sharedPrefs;

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
        if (!sharedPrefs.onReadString("login").equals("")) {
            username.setText(sharedPrefs.onReadString("login"));
            checkBox.setChecked(true);
        }
    }

    @Override
    public void initialize() {
        sharedPrefs = new SharedPrefs(this);
        checkBox = (CheckBox) findViewById(R.id.saveUsername);
        logIn = (Button) findViewById(R.id.logIn);
        register = (Button) findViewById(R.id.registerButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

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
//                    SharedPrefs.storeLogin(username.getText().toString());
                    sharedPrefs.onStoreData("login",username.getText().toString());
                } else {
//                    sharedPrefs.deleteString("login");
                    sharedPrefs.onDeleteString("login");
                }
                loginPresenter.onLogin(username.getText().toString(), password.getText().toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void loginSucces(Player player) {
        //startNewActivity(this, MainActivity.class);
        Intent intent = new Intent(this, MyTeamsActivity.class);
        startActivity(intent);
//        finish();
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, R.string.invalidCredentialis, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.emptyBox));
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.emptyBox));
    }
}
