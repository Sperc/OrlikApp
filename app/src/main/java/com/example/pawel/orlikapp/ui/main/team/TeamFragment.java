package com.example.pawel.orlikapp.ui.main.team;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.ui.main.myteams.MyTeamsFragment;
import com.example.pawel.orlikapp.ui.main.teamsnotification.TeamNotificationFragment;

import butterknife.BindView;
import butterknife.BindViews;
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
