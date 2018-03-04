package com.example.pawel.orlikapp.api.client.client_impl;

import com.example.pawel.orlikapp.api.BaseApiListener;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.api.client.AppUserClient;
import com.example.pawel.orlikapp.api.client.PlayerClient;
import com.example.pawel.orlikapp.model.wraper.ChangePassword;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseListener;
import com.example.pawel.orlikapp.ui.base.BaseView;
import com.example.pawel.orlikapp.utils.CodeStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 04.03.2018.
 */

public class AppUserServiceImpl {
    private AppUserClient appUserClient;

    public AppUserServiceImpl() {
        this.appUserClient = ServiceGenerator.createService().create(AppUserClient.class);
    }

    public interface ChangePasswordListener extends BaseListener {
        void onSucces();
    }

    public interface DeleteAccountListener extends BaseListener {
        void onSucces();
    }

    public void changePassword(ChangePassword changePassword, ChangePasswordListener changePasswordListener) {
        Call<Void> call = appUserClient.changePassword(PreferencesShared.onReadString(PreferencesSharedKyes.token), changePassword);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    changePasswordListener.onSucces();
                else
                    changePasswordListener.onFailure(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                changePasswordListener.onServerNotResponse();
            }
        });
    }

    public void deleteAccount(DeleteAccountListener deleteAccountListener) {
        Call<Void> call = appUserClient.deleteAccount(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    deleteAccountListener.onSucces();
                else
                    deleteAccountListener.onFailure(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteAccountListener.onServerNotResponse();
            }
        });
    }
}
