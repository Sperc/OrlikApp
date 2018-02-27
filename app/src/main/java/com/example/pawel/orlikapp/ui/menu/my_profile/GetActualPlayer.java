package com.example.pawel.orlikapp.ui.menu.my_profile;

import android.widget.Toast;

import com.example.pawel.orlikapp.api.client_impl.PlayerServiceImpl;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.utils.DateHelper;
import com.example.pawel.orlikapp.utils.Logs;
import com.squareup.picasso.Picasso;

import java.util.Optional;

/**
 * Created by Pawel on 27.02.2018.
 */

public class GetActualPlayer implements PlayerServiceImpl.ActualPlayerListener {
    MyProfileFragment fragment;

    public GetActualPlayer(MyProfileFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onSuccess(Player player) {
        fragment.viewHolder.email.setText(player.getUsername());
        Optional.ofNullable(player.getPicture()).ifPresent(picture -> Picasso.with(fragment.getActivity()).load(ServiceGenerator.BASE_URL_IMAGE + picture.getId()).into(fragment.viewHolder.playerPhoto));
        fragment.viewHolder.age.setText(String.valueOf(DateHelper.getActualAgeFromBirthday(player.getBirthDate())));
        fragment.viewHolder.nameOfUser.setText(player.toString());
        Optional.ofNullable(player.getPhoneNumber()).ifPresent(s -> fragment.viewHolder.phoneNumber.setText(player.getPhoneNumber()));
        Logs.d("MyProfileFragment","SUCCESFUL");

    }

    @Override
    public void onServerNotResponse() {
        Toast.makeText(fragment.getActivity(), "Connection Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnauthorized() {
        //brak autoryzacji
        Logs.d("MyProfileFragment", "UNAUTHORIZED");
    }

    @Override
    public void onServerError() {
        Logs.d("MyProfileFragment", "Connection Error");
    }


}
