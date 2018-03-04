package com.example.pawel.orlikapp.ui.menu.settings.validation;

import com.example.pawel.orlikapp.model.wraper.ChangePassword;

/**
 * Created by Pawel on 04.03.2018.
 */

public interface ChangePasswordInteractor {
    interface ChangePasswordValidateListener {
        void onErrorRepeatPassword();

        void onPasswordDifficultError();
    }


    boolean onValidate(ChangePassword changePassword, ChangePasswordValidateListener listener);
}
