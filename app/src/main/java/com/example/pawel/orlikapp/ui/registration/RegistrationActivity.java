package com.example.pawel.orlikapp.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.login.LoginActivity;

public class RegistrationActivity extends BaseActivity implements RegistrationView, RegistrationPresenter.PresenterListener {
    private Button register;
    private EditText repeatPassword;
    private EditText password;
    private EditText email;
    private RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialize();
        setPresenter();
        onButtonClick();
    }

    @Override
    public void initialize() {
        register = (Button) findViewById(R.id.registationButton);
        password = (EditText) findViewById(R.id.registerPassword);
        repeatPassword = (EditText) findViewById(R.id.confirmPassword);
        email = (EditText) findViewById(R.id.emailAddres);
    }

    @Override
    public void onButtonClick() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUser appUser = new AppUser(email.getText().toString(),
                        password.getText().toString(),
                        repeatPassword.getText().toString());

                registrationPresenter.onRegister(appUser);
            }
        });
    }

    @Override
    public void setPresenter() {
        registrationPresenter = new RegistrationPresenter(this, this, this);
    }


    @Override
    public void setEmailError() {
        email.setError(getString(R.string.emailError));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.emptyBox));
    }

    @Override
    public void setRepeatPasswordError() {
        repeatPassword.setError(getString(R.string.repeatPasswordError));
    }

    @Override
    public void onSuccesRegistration() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "succesfull", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailure() {
        email.setError(getString(R.string.usernameExistsError));
    }
}
