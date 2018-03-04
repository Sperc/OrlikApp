package com.example.pawel.orlikapp.ui.menu.settings;

import com.example.pawel.orlikapp.ui.base.BaseView;

/**
 * Created by Pawel on 03.03.2018.
 */

public interface SettingsView extends BaseView{
    void onSuccesPasswordChanged();
    void onConfirmPasswordError();
    void onCurrentPasswordError();
    void onDificutPasswordError();
    void onConfirmDetelteError();
    void onSuccesDelete();
}
