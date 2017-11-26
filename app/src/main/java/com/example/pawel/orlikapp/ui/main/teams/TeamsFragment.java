package com.example.pawel.orlikapp.ui.main.teams;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Team;

import java.util.Set;

public class TeamsFragment extends Fragment implements TeamsPresenter.TeamsPresenterListener{

    private TeamsPresenter teamsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        teamsPresenter = new TeamsPresenter(getContext(),this);
        teamsPresenter.getMyTeams();

        return view;
    }


    @Override
    public void onSucces(Set<Team> teams) {
        Toast.makeText(getContext(), "accept", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
    }
}
