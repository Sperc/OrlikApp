package com.example.pawel.orlikapp.ui.menu.find_playground;

import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlaygroundClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 08.12.2017.
 */

public class FindPlaygroundPresenter {
    private FindPlaygroundListener findPlaygroundListener;

    public FindPlaygroundPresenter(FindPlaygroundListener findPlaygroundListener) {
        this.findPlaygroundListener = findPlaygroundListener;
    }

    public void getPlaygroundByCity(String city){
        PlaygroundClient playgroundClient = ServiceGenerator.createService().create(PlaygroundClient.class);
        Call<List<Playground>> call = playgroundClient.getAllPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.token),city);

        call.enqueue(new Callback<List<Playground>>() {
            @Override
            public void onResponse(Call<List<Playground>> call, Response<List<Playground>> response) {
                if(response.isSuccessful()){
                    findPlaygroundListener.onSucces(response.body());
                }
                else if(response.raw().code()==401){
                    findPlaygroundListener.onAuthorizationProblem();
                }
                else {
                    findPlaygroundListener.onAuthorizationProblem();
                }
            }

            @Override
            public void onFailure(Call<List<Playground>> call, Throwable t) {
                findPlaygroundListener.onFailure();
            }
        });
    }
    public interface FindPlaygroundListener{
        void onSucces(List<Playground> playgrounds);
        void onFailure();
        void onAuthorizationProblem();
    }
}
