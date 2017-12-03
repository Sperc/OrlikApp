package com.example.pawel.orlikapp.ui.select_city;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.City;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.CityClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 01.12.2017.
 */

public class SelectCityPresenter {
    private Context context;
    private SelectCityListener selectCityListener;

    public SelectCityPresenter(Context context, SelectCityListener selectCityListener) {
        this.context = context;
        this.selectCityListener = selectCityListener;
    }

    public void getAllCity(){
        CityClient cityClient = ServiceGenerator.createService().create(CityClient.class);
        Call<List<City>> call = cityClient.getAllCity(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if(response.isSuccessful()){
                    selectCityListener.onSucces(response.body());
                }
                else {
                    selectCityListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(context,context.getString(R.string.serverProblem), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface SelectCityListener{
        void onSucces(List<City> cityList);
        void onFailure();
    }

}
