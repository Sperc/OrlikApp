package com.example.pawel.orlikapp.ui.my_team;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Team;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyTeamsActivity extends BaseActivity implements MyTeamPresenter.MyTeamPresenterLisener {

    private MyTeamPresenter myTeamPresenter;
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_teams);
        setPresenter();
        initialize();
        onButtonClick();
        myTeamPresenter.getMyTeams();
    }
    @Override
    public void initialize() {
        listView = (ListView)findViewById(R.id.teams_list);
    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setPresenter() {
        myTeamPresenter = new MyTeamPresenter(this,this);
    }

    @Override
    public void onSucces(Set<Team> teamSet) {
        List<Team> list = new ArrayList<>(teamSet);
        ArrayAdapter<Team> listadapter  = new ArrayAdapter<Team>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(listadapter);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
    }
}
