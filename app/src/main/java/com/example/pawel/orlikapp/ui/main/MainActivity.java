package com.example.pawel.orlikapp.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.prefs.SharedPrefs;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        SharedPrefs sharedPrefs = new SharedPrefs(this);
        textView.setText(sharedPrefs.onReadString("token"));
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
