package com.example.pawel.orlikapp.ui.menu.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.wraper.ChangePassword;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseFragment;
import com.example.pawel.orlikapp.ui.login.LoginActivity;

public class SettingsFragment extends Fragment implements SettingsView{

    private ViewHolder viewHolder;
    private SettingsPresenter settingsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        viewHolder = new ViewHolder(view);
        settingsPresenter = new SettingsPresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        onClick();
    }

    void onClick(){
        viewHolder.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword changePassword = new ChangePassword();
                changePassword.setCurrentPassword(viewHolder.currentPassword.getText().toString());
                changePassword.setNewPassword(viewHolder.newPasword.getText().toString());
                changePassword.setRepeatPassword(viewHolder.confirmPassword.getText().toString());
                settingsPresenter.changePassword(changePassword);
            }
        });
        viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsPresenter.deleteAccount(viewHolder.deleteEditText.getText().toString());
            }
        });
    }


    @Override
    public void onServerError() {
        Toast.makeText(getContext(), getString(R.string.serverProblem), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServerNotResponse() {
        Toast.makeText(getContext(), getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnauthorized() {
        Toast.makeText(getContext(), "Brak autoryzacji", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccesPasswordChanged() {
        Toast.makeText(getContext(), "Hasło zostało zmienione", Toast.LENGTH_SHORT).show();
        viewHolder.confirmPassword.setText("");
        viewHolder.currentPassword.setText("");
        viewHolder.newPasword.setText("");
    }

    @Override
    public void onConfirmPasswordError() {
        viewHolder.confirmPassword.setError(getString(R.string.repeatPasswordError));
    }

    @Override
    public void onCurrentPasswordError() {
        viewHolder.currentPassword.setError(getString(R.string.currentPasswordError));
    }

    @Override
    public void onDificutPasswordError() {
        viewHolder.newPasword.setError(getString(R.string.newPasswordError));
    }

    @Override
    public void onConfirmDetelteError() {
        viewHolder.deleteEditText.setError(getString(R.string.confirmDeleteError));
    }

    @Override
    public void onSuccesDelete() {
            PreferencesShared.onDeleteString(PreferencesSharedKyes.token);
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
    }
}
