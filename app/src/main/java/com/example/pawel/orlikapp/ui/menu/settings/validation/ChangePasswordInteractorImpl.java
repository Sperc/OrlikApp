package com.example.pawel.orlikapp.ui.menu.settings.validation;

import com.example.pawel.orlikapp.model.wraper.ChangePassword;

/**
 * Created by Pawel on 04.03.2018.
 */

public class ChangePasswordInteractorImpl implements ChangePasswordInteractor {
    @Override
    public boolean onValidate(ChangePassword changePassword, ChangePasswordValidateListener listener) {
        boolean flag = true;
        if(changePassword.getCurrentPassword().equals("")){
            flag = false;
            listener.onPasswordDifficultError();
        }
        if(!changePassword.getNewPassword().equals(changePassword.getRepeatPassword())){
            flag = false;
            listener.onErrorRepeatPassword();
        }
        return flag;
    }
}
