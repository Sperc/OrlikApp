package com.example.pawel.orlikapp.ui.menu.myteams;

import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.model.Team;
import com.example.pawel.orlikapp.ui.menu.details_playground.DetailsPlaygroundFragment;
import com.example.pawel.orlikapp.ui.menu.details_team.DetailsTeamFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTeamsFragment extends Fragment implements MyTeamsPresenter.TeamsPresenterListener {

    private MyTeamsPresenter teamsPresenter;
    private ArrayAdapter arrayAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myteams, container, false);
        init(view);
        teamsPresenter = new MyTeamsPresenter(getContext(), this);
        teamsPresenter.getMyTeams();
        onClick();
        return view;
    }

    @Override
    public void onSucces(Set<Team> teams) {
        List<Team> list = new ArrayList<>();
        list.addAll(teams);
        arrayAdapter = new ArrayAdapter<Team>(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
    }

    private void init(View view) {
        listView = (ListView) view.findViewById(R.id.teams_list);
    }
    private void onClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Team team = (Team) adapterView.getItemAtPosition(i);
                //Toast.makeText(getContext(), team.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
