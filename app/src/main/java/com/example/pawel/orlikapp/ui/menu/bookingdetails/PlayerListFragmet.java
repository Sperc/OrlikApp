package com.example.pawel.orlikapp.ui.menu.bookingdetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerListFragmet extends Fragment {
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list_fragmet, container, false);
        init(view);
        Bundle bundle = getArguments();
        List<Player> list = (List<Player>) bundle.getSerializable("player_list");
        CustomAdapter customAdapter = new CustomAdapter(list,getContext());
        listView.setAdapter(customAdapter);
        return view;
    }
    private void init(View view){
        listView = (ListView)view.findViewById(R.id.playerList);
    }

}
