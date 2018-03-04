package com.example.pawel.orlikapp.ui.menu.settings;

import com.example.pawel.orlikapp.api.client.AppUserClient;
import com.example.pawel.orlikapp.api.client.client_impl.AppUserServiceImpl;
import com.example.pawel.orlikapp.model.wraper.ChangePassword;
import com.example.pawel.orlikapp.ui.menu.settings.validation.ChangePasswordInteractor;
import com.example.pawel.orlikapp.ui.menu.settings.validation.ChangePasswordInteractorImpl;
import com.example.pawel.orlikapp.utils.CodeStatus;

/**
 * Created by Pawel on 03.03.2018.
 */

public class SettingsPresenter {
    private SettingsView settingsView;
    private AppUserServiceImpl appUserService;

    public SettingsPresenter(SettingsView settingsView) {
        this.settingsView = settingsView;
        this.appUserService = new AppUserServiceImpl();
    }

    public void changePassword(ChangePassword changePassword) {
        ChangePasswordInteractor changePasswordInteractor = new ChangePasswordInteractorImpl();
        if (!changePasswordInteractor.onValidate(changePassword, validateListener)) return;
        appUserService.changePassword(changePassword, changePasswordListener);
    }

    public void deleteAccount(String confirmDelete) {
        if (!confirmDelete.equals("DELETE")) {
            settingsView.onConfirmDetelteError();
            return;
        }
        appUserService.deleteAccount(deleteAccountListener);
    }

    AppUserServiceImpl.ChangePasswordListener changePasswordListener = new AppUserServiceImpl.ChangePasswordListener() {
        @Override
        public void onSucces() {
            settingsView.onSuccesPasswordChanged();
        }

        @Override
        public void onFailure(int status) {
            if (status == CodeStatus.BAD_REQUEST)
                settingsView.onCurrentPasswordError();
            else if (status == CodeStatus.UNAUTHORIZED)
                settingsView.onUnauthorized();
            else
                settingsView.onServerError();
        }

        @Override
        public void onServerNotResponse() {
            settingsView.onServerNotResponse();
        }
    };


    ChangePasswordInteractor.ChangePasswordValidateListener validateListener = new ChangePasswordInteractor.ChangePasswordValidateListener() {
        @Override
        public void onErrorRepeatPassword() {
            settingsView.onConfirmPasswordError();
        }

        @Override
        public void onPasswordDifficultError() {
            settingsView.onDificutPasswordError();
        }
    };

    AppUserServiceImpl.DeleteAccountListener deleteAccountListener = new AppUserServiceImpl.DeleteAccountListener() {
        @Override
        public void onSucces() {
            settingsView.onSuccesDelete();
        }

        @Override
        public void onFailure(int status) {
            if (status == CodeStatus.BAD_REQUEST)
                settingsView.onCurrentPasswordError();
            else if (status == CodeStatus.UNAUTHORIZED)
                settingsView.onUnauthorized();
            else
                settingsView.onServerError();
        }

        @Override
        public void onServerNotResponse() {
            settingsView.onServerNotResponse();
        }
    };
}
