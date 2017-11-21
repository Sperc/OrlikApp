package com.example.pawel.orlikapp.ui.my_team;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.pawel.orlikapp.engine.ServiceGenerator;
import com.example.pawel.orlikapp.engine.http.TeamClient;
import com.example.pawel.orlikapp.model.Team;
import com.example.pawel.orlikapp.prefs.SharedPrefs;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 20.11.2017.
 */

public class MyTeamPresenter {

    public interface MyTeamPresenterLisener {
        void onSucces(Set<Team> teamSet);
        void onFailure();
    }

    private Context context;
    private SharedPrefs sharedPrefs;
    private MyTeamPresenterLisener myTeamPresenterLisener;

    public MyTeamPresenter(Context context,MyTeamPresenterLisener myTeamPresenterLisener) {
        this.myTeamPresenterLisener = myTeamPresenterLisener;
        this.context = context;
        this.sharedPrefs = new SharedPrefs(this.context);
    }

    public void getMyTeams() {
        TeamClient teamClient = ServiceGenerator.createService().create(TeamClient.class);
        Call<Set<Team>> call = teamClient.getMyTeams(sharedPrefs.onReadString("token"));

        call.enqueue(new Callback<Set<Team>>() {
            @Override
            public void onResponse(Call<Set<Team>> call, Response<Set<Team>> response) {
                if (response.isSuccessful()) {
                    myTeamPresenterLisener.onSucces(response.body());
                }
                else {
                    myTeamPresenterLisener.onFailure();
                }

            }

            @Override
            public void onFailure(Call<Set<Team>> call, Throwable t) {

            }
        });
    }
}
