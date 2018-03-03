package com.example.pawel.orlikapp.ui.menu.my_profile;

import com.example.pawel.orlikapp.api.BaseApiListener;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.ui.base.BaseView;

/**
 * Created by Pawel on 03.03.2018.
 */

public interface MyProfileView extends BaseView{
    void onGetPlayer(Player player);
}
