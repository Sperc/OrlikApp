package com.example.pawel.orlikapp.ui.menu.bookingdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Picture;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Optional;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pawel on 19.02.2018.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Player> playerList;
    private Context context;
    private DataChangedListener dataChangedListener;
    private String leaderName;
    private boolean leader;

    public CustomAdapter(List<Player> playerList, Context context, boolean leader, String leaderName, DataChangedListener dataChangedListener) {
        super();
        this.leader = leader;
        this.leaderName = leaderName;
        this.dataChangedListener = dataChangedListener;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view1 = layoutInflater.inflate(R.layout.list_item_player, null);
        TextView name = (TextView) view1.findViewById(R.id.playerName);
        TextView email = (TextView) view1.findViewById(R.id.playerEmail);
        ImageButton imageButton = (ImageButton) view1.findViewById(R.id.deleteBtn);
        CircleImageView circleImageView = (CircleImageView) view1.findViewById(R.id.playerPhoto);
        TextView leaderNameTextView = (TextView) view1.findViewById(R.id.leaderTextView);

        Player p = playerList.get(i);
        name.setText(p.toString());
        email.setText(p.getUsername());
        Optional<Picture> picture = Optional.ofNullable(p.getPicture());
        picture.ifPresent(picture1 -> Picasso.with(context).load(ServiceGenerator.BASE_URL_IMAGE + picture1.getId()).into(circleImageView));
        //tutaj onClickListener
        setLeaderTextView(leaderNameTextView, p.getUsername());
        if (leader) {
            setDeleteBtn(imageButton, p.getUsername());
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerList.remove(i);
                dataChangedListener.dataChanged();

            }
        });
        return view1;

    }

    private void setLeaderTextView(View leaderTv, String playerName) {
        if (leaderName.equals(playerName)) {
            leaderTv.setVisibility(View.VISIBLE);
        } else {
            leaderTv.setVisibility(View.GONE);
        }

    }

    private void setDeleteBtn(View deleteBtn, String playerName) {
        if (!playerName.equals(leaderName))
            deleteBtn.setVisibility(View.VISIBLE);
    }

    public interface DataChangedListener {
        void dataChanged();
    }
}
