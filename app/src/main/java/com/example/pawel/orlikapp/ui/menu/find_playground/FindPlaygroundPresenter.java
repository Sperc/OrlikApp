package com.example.pawel.orlikapp.ui.menu.find_playground;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.api.client.PlaygroundClient;
import com.example.pawel.orlikapp.api.ServiceGenerator;

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
                    findPlaygroundView.onResponsePresenter(R.string.authorizationProblem);
                }
                else {
                    findPlaygroundView.onResponsePresenter(R.string.error);
                }
            }

            @Override
            public void onFailure(Call<List<Playground>> call, Throwable t) {
                findPlaygroundView.onResponsePresenter(R.string.connectionError);
            }
        });
    }
    public void getPlaygroundByCityAndCategory(String city, String category, final FindPlaygroundListener findPlaygroundListener){
        PlaygroundClient playgroundClient = ServiceGenerator.createService().create(PlaygroundClient.class);
        Call<List<Playground>> call  = playgroundClient.getAllPlaygroudByCityAndCategory(PreferencesShared.onReadString(PreferencesSharedKyes.token),city,category);
        call.enqueue(new Callback<List<Playground>>() {
            @Override
            public void onResponse(Call<List<Playground>> call, Response<List<Playground>> response) {
                if(response.isSuccessful()){
                    findPlaygroundListener.onSucces(response.body());
                }
                else if(response.raw().code()==401){
                    findPlaygroundView.onResponsePresenter(R.string.authorizationProblem);
                }
                else {
                    findPlaygroundView.onResponsePresenter(R.string.error);
                }
            }

            @Override
            public void onFailure(Call<List<Playground>> call, Throwable t) {
                findPlaygroundView.onResponsePresenter(R.string.connectionError);
            }
        });
    }


    public interface FindPlaygroundListener{
        void onSucces(List<Playground> playgrounds);
    }


}
