package com.example.pawel.orlikapp.ui.menu.my_profile;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.ui.menu.bookingdetails.PlayerListFragmet;
import com.example.pawel.orlikapp.ui.menu.editplayer.EditPlayerFragment;
import com.example.pawel.orlikapp.ui.menu.main.MainActivity;
import com.example.pawel.orlikapp.utils.DateHelper;
import com.example.pawel.orlikapp.utils.Logs;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Optional;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment implements MyProfileView {

    private MyProfilePresenter myProfilePresenter;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    View view1;
    ViewHolder viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_my_profile, container, false);
        viewHolder = new ViewHolder(view1);
        myProfilePresenter = new MyProfilePresenter(this);

//
//        Button button = view1.findViewById(R.id.test);
//        button.setOnClickListener(view -> OpenFragment());
        return view1;
    }

    @Override
    public void onResume() {

        super.onResume();
        myProfilePresenter.getUser();
    }

    public void openEditPlayerFragment(Player player) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        EditPlayerFragment editPlayerFragment = new EditPlayerFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("player", player);
        editPlayerFragment.setArguments(bundle);
        ft.replace(R.id.frame, editPlayerFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onServerError() {

    }

    @Override
    public void onServerNotResponse() {

    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onGetPlayer(Player player) {
//        viewHolder.actualBookings.setText(String.valueOf(player.getBookingList().size()));
        viewHolder.email.setText(player.getUsername());
        Optional.ofNullable(player.getPicture()).ifPresent(picture -> Picasso.with(getActivity()).load(ServiceGenerator.BASE_URL_IMAGE + picture.getId()).into(viewHolder.playerPhoto));
        viewHolder.age.setText(String.valueOf(DateHelper.getActualAgeFromBirthday(player.getBirthDate())));
        viewHolder.nameOfUser.setText(player.toString());
        Optional.ofNullable(player.getPhoneNumber()).ifPresent(s -> viewHolder.phoneNumber.setText(player.getPhoneNumber()));
        Logs.d("MyProfileFragment", "SUCCESFUL");
        viewHolder.test.setOnClickListener(view -> {
            openEditPlayerFragment(player);
            onStop();
        });
    }
}
