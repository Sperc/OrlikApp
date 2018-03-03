package com.example.pawel.orlikapp.ui.menu.editplayer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.ui.menu.my_profile.MyProfileFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPlayerFragment extends Fragment implements EditPlayerView {

    private Player player;


    public EditPlayerFragment() {
        // Required empty public constructor
    }

    ViewHolder viewHolder;
    private EditPlayerPresenter editPlayerPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_player, container, false);
        viewHolder = new ViewHolder(view);
        Bundle bundle = getArguments();
        player = (Player) bundle.getSerializable("player");
        editPlayerPresenter = new EditPlayerPresenter(this);
        initializePlayer();
        onClick();
        return view;
    }
    private void initializePlayer(){
        viewHolder.firstName.setText(player.getFirstName());
        viewHolder.lastName.setText(player.getLastName());
        viewHolder.birthDate.setText(player.getBirthDate());
        viewHolder.phoneNumber.setText(player.getPhoneNumber());
    }


    public void openEditPlayerFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        MyProfileFragment myProfileFragment = new MyProfileFragment();
        Bundle bundle = new Bundle();
        ft.replace(R.id.flcontent, myProfileFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    void onClick() {
        viewHolder.accept.setOnClickListener(view -> {
            Player p = new Player();
            p.setFirstName(viewHolder.firstName.getText().toString());
            p.setLastName(viewHolder.lastName.getText().toString());
            p.setBirthDate(viewHolder.birthDate.getText().toString());
            p.setPhoneNumber(viewHolder.phoneNumber.getText().toString());
            editPlayerPresenter.editPlayer(p);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(new MyProfileFragment()).attach(new MyProfileFragment()).commit();
        });
    }

    @Override
    public void setSucces() {
        Toast.makeText(getActivity(), "Sukces!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void setServerError() {
        Toast.makeText(getActivity(), R.string.serverProblem, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setServerNotResponse() {
        Toast.makeText(getActivity(), R.string.connectionError, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setUnauthorized() {
        Toast.makeText(getActivity(), R.string.serverProblem, Toast.LENGTH_SHORT).show();

    }
}
