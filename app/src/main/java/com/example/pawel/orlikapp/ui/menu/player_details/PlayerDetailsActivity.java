package com.example.pawel.orlikapp.ui.menu.player_details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.utils.DateHelper;
import com.example.pawel.orlikapp.utils.Logs;
import com.squareup.picasso.Picasso;

public class PlayerDetailsActivity extends AppCompatActivity implements PlayerDetailsView {

    private ViewHolder viewHolder;
    private PlayerDetailsPresenter playerDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        viewHolder = new ViewHolder(this);
        playerDetailsPresenter = new PlayerDetailsPresenter(this);
        String username = getIntent().getStringExtra("player_name");
        if (username != null)
            playerDetailsPresenter.getPlayerByUsername(username);
    }

    private void initializeView(Player player) {
        viewHolder.email.setText(player.getUsername());
        Picasso.with(this).load(ServiceGenerator.BASE_URL_IMAGE + player.getPicture().getId()).into(viewHolder.playerPhoto);
        viewHolder.age.setText(String.valueOf(DateHelper.getActualAgeFromBirthday(player.getBirthDate())));
        viewHolder.nameOfUser.setText(player.toString());
        if (viewHolder.phoneNumber != null)
            viewHolder.phoneNumber.setText(player.getPhoneNumber());
        else {
            viewHolder.phoneNumber.setText("Brak");
        }

        Logs.d("PlayerDetaisActivity", "SUCCESFUL");

    }

    @Override
    public void onServerError() {

    }

    @Override
    public void onServerNotResponse() {

    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void getPlayer(Player player) {
        initializeView(player);
    }
}
