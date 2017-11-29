package com.example.pawel.orlikapp.ui.menu.team;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.ui.menu.myteams.MyTeamsFragment;
import com.example.pawel.orlikapp.ui.menu.teamsnotification.TeamNotificationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamFragment extends Fragment {
    private PagerAdapter pagerAdapter;
    @BindView(R.id.container)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        ButterKnife.bind(this, view);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void setupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new MyTeamsFragment(), "Moje Grupy");
        adapter.addFragment(new TeamNotificationFragment(), "Zaproszenia");
        viewPager.setAdapter(adapter);
    }
}
