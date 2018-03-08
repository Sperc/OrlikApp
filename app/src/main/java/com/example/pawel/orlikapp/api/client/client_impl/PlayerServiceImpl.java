package com.example.pawel.orlikapp.api.client.client_impl;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.api.client.PlayerClient;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.api.BaseApiListener;
import com.example.pawel.orlikapp.utils.CodeStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 27.02.2018.
 */

public class PlayerServiceImpl {

    private PlayerClient playerClient;

    public PlayerServiceImpl() {
        this.playerClient = ServiceGenerator.createService().create(PlayerClient.class);
    }

    public interface ActualPlayerListener extends BaseApiListener {
        void onSuccess(Player player);
    }

    public interface EditPlayerListener extends BaseApiListener {
        void onSucces();
    }

    public interface UserReservationListener extends BaseApiListener {
        void onSucces(List<Booking> bookingList);
    }

    public interface PlayerListener extends BaseApiListener {
        void onSucces(Player player);
    }

    public void getActualPlayer(ActualPlayerListener actualPlayerListener) {
        Call<Player> call = playerClient.getActualPlayer(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()) {
//                    actualPlayerListener.getPlayer(response.body());
                    actualPlayerListener.onSuccess(response.body());
                } else if (response.code() == CodeStatus.UNAUTHORIZED) {
                    actualPlayerListener.onUnauthorized();
                } else {
                    actualPlayerListener.onServerError();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                actualPlayerListener.onServerNotResponse();
            }
        });
    }

    public void editPlayer(Player player, EditPlayerListener editPlayerListener) {
        Call<Void> call = playerClient.editPlayer(PreferencesShared.onReadString(PreferencesSharedKyes.token), player);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    editPlayerListener.onSucces();
                else if (response.code() == CodeStatus.UNAUTHORIZED)
                    editPlayerListener.onUnauthorized();
                else
                    editPlayerListener.onServerError();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                editPlayerListener.onServerNotResponse();
            }
        });

    }

    public void getPlayerBooking(UserReservationListener userReservationListener) {
        Call<List<Booking>> call = playerClient.getUserReservation(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful())
                    userReservationListener.onSucces(response.body());
                else if (response.code() == CodeStatus.UNAUTHORIZED)
                    userReservationListener.onUnauthorized();
                else
                    userReservationListener.onServerError();
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                userReservationListener.onServerNotResponse();
            }
        });
    }

    public void getPlayerByUsername(String username, PlayerListener playerListener) {
        Call<Player> call = playerClient.getPlayerByUsername(PreferencesShared.onReadString(PreferencesSharedKyes.token), username);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()) {
//                    actualPlayerListener.getPlayer(response.body());
                    playerListener.onSucces(response.body());
                } else if (response.code() == CodeStatus.UNAUTHORIZED) {
                    playerListener.onUnauthorized();
                } else {
                    playerListener.onServerError();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                playerListener.onServerNotResponse();
            }
        });
    }
}
