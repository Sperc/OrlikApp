package com.example.pawel.orlikapp.ui.menu.find_playground;

import android.app.Activity;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlaygroundClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 08.12.2017.
 */

public class FindPlaygroundPresenter {

    FindPlaygroundView findPlaygroundView;

    public FindPlaygroundPresenter(FindPlaygroundView findPlaygroundView) {
        this.findPlaygroundView = findPlaygroundView;
    }

    public void getPlaygroundByCity(String city, final FindPlaygroundListener findPlaygroundListener){
        PlaygroundClient playgroundClient = ServiceGenerator.createService().create(PlaygroundClient.class);
        Call<List<Playground>> call = playgroundClient.getAllPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.token),city);

        call.enqueue(new Callback<List<Playground>>() {
            @Override
            public void onResponse(Call<List<Playground>> call, Response<List<Playground>> response) {
                if(response.isSuccessful()){
                    findPlaygroundListener.onSucces(response.body());
                }
                else if(response.raw().code()==401){
                    findPlaygroundView.onResponse(String.valueOf(R.string.authorizationProblem));
                }
                else {
                    findPlaygroundView.onResponse(String.valueOf(R.string.error));
                }
            }

            @Override
            public void onFailure(Call<List<Playground>> call, Throwable t) {
                findPlaygroundView.onResponse(String.valueOf(R.string.connectionError));
            }
        });
    }


    public interface FindPlaygroundListener{
        void onSucces(List<Playground> playgrounds);
    }


}
