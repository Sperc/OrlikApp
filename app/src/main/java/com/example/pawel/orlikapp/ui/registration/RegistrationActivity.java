package com.example.pawel.orlikapp.ui.registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class RegistrationActivity extends BaseActivity implements RegistrationActivityInterface {
    private Button register;
    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private RegistrationPresenter registrationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialize();
    }

    @Override
    public void initialize() {
        register = (Button)findViewById(R.id.registerButton);
        username = (EditText)findViewById(R.id.registartionUsername);
        password = (EditText)findViewById(R.id.registerPassword);
        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        email = (EditText)findViewById(R.id.emailAddres);
    }

    @Override
    public void onClick() {

    }

    @Override
    public void setPresenter() {

    }
}
