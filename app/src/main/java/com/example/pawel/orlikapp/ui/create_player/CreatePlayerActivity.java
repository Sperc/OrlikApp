package com.example.pawel.orlikapp.ui.create_player;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class CreatePlayerActivity extends BaseActivity implements CreatePlayerView {
    private CreatePlayerPresenter createPlayerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);
        setPresenter();
        initialize();
        onButtonClick();
        createPlayerPresenter.createPlayer(new Player("Pawel", "Sosnowka", PreferencesShared.onReadString(PreferencesSharedKyes.username)));
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setPresenter() {
        createPlayerPresenter = new CreatePlayerPresenter(this);
    }

    @Override
    public void onPlayerCreate() {
        Toast.makeText(this, "Dodano Gracza", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConntectionError() {
        Toast.makeText(this, getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void badRequest() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unuthorized() {
        Toast.makeText(this, getString(R.string.authorizationProblem), Toast.LENGTH_SHORT).show();

    }
}
