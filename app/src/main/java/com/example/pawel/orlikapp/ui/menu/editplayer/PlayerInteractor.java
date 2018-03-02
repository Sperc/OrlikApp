package com.example.pawel.orlikapp.ui.menu.editplayer;

import com.example.pawel.orlikapp.model.Player;

/**
 * Created by Pawel on 01.03.2018.
 */

public interface PlayerInteractor {
    interface PlayerInteractorListener {
        void onFirstNameError();

        void onLastNameError();

        void onPhoneNumberError();

        void onBirthDateError();
    }
    boolean validate(Player p,PlayerInteractorListener playerInteractorListener);
}
