package com.example.pawel.orlikapp.ui.main.teams;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;

import com.example.pawel.orlikapp.model.Team;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.TeamClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 26.11.2017.
 */

public class TeamsPresenter {

    private Context context;
    private TeamsPresenterListener teamsPresenterListener;

    public TeamsPresenter(Context context,TeamsPresenterListener teamsPresenterListener) {
        this.teamsPresenterListener = teamsPresenterListener;
        this.context = context;
    }
    public void getMyTeams() {
        TeamClient teamClient = ServiceGenerator.createService().create(TeamClient.class);
        Call<Set<Team>> call = teamClient.getMyTeams(PreferencesShared.onReadString(PreferencesSharedKyes.token));

        call.enqueue(new Callback<Set<Team>>() {
            @Override
            public void onResponse(Call<Set<Team>> call, Response<Set<Team>> response) {
                if (response.isSuccessful()) {
                    teamsPresenterListener.onSucces(response.body());
                } else {
                    teamsPresenterListener.onFailure();
                }

            }

            @Override
            public void onFailure(Call<Set<Team>> call, Throwable t) {
                Toast.makeText(context, "Brak połaczenia z serwerem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface TeamsPresenterListener{
        void onSucces(Set<Team> teams);
        void onFailure();
    }
}
