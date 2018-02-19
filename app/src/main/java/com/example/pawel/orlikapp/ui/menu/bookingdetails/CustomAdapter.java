package com.example.pawel.orlikapp.ui.menu.bookingdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.utils.ImageHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pawel on 19.02.2018.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Player> playerList;
    private Context context;

    public CustomAdapter(List<Player> playerList, Context context) {
        super();
        this.playerList = playerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view1 = layoutInflater.inflate(R.layout.list_item_player,null);
        TextView name = (TextView)view1.findViewById(R.id.playerName);
        TextView email = (TextView)view1.findViewById(R.id.playerEmail);
        CircleImageView circleImageView = (CircleImageView)view1.findViewById(R.id.playerPhoto);
        Player p = playerList.get(i);
        name.setText(p.toString());
        email.setText(p.getUsername());
        circleImageView.setImageBitmap(ImageHelper.convertStringToBitmap(p.getImage()));
        //tutaj onClickListener
        return view1;

    }
}
