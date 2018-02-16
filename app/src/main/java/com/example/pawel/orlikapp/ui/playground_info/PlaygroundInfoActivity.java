package com.example.pawel.orlikapp.ui.playground_info;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.ui.menu.details_playground.DetailsPlaygroundFragment;
import com.example.pawel.orlikapp.ui.playground_info.playground_info.InfoPlaygroundFragment;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaygroundInfoActivity extends AppCompatActivity {



    private Playground p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        p = (Playground) intent.getSerializableExtra("playground");
        onStartInfoPlaygroundFragment(p);

    }
    private void onStartInfoPlaygroundFragment(Playground pl) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        InfoPlaygroundFragment infoPlaygroundFragment = new InfoPlaygroundFragment();
//        InitializeDetailsPlaygroundFragment initializeDetailsPlaygroundFragment = new InitializeDetailsPlaygroundFragment();
        Bundle bundle = new Bundle();
        Playground playground = (Playground) pl;

        bundle.putSerializable("playground", playground);
        infoPlaygroundFragment.setArguments(bundle);
        ft.replace(R.id.playground_container, infoPlaygroundFragment);
        ft.addToBackStack(null);
        ft.commit();


//
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flcontent, detailsPlaygroundFragment).commit();

    }
}
