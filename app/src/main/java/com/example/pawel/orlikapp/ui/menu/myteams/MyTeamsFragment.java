package com.example.pawel.orlikapp.ui.menu.myteams;

import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Team;

import java.util.Set;

public class MyTeamsFragment extends Fragment implements MyTeamsPresenter.TeamsPresenterListener{

    private MyTeamsPresenter teamsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myteams, container, false);
        teamsPresenter = new MyTeamsPresenter(getContext(),this);
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
