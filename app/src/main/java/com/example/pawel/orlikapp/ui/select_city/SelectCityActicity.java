package com.example.pawel.orlikapp.ui.select_city;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.City;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.menu.main.MainActivity;
import com.example.pawel.orlikapp.utils.Logs;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityActicity extends BaseActivity implements SelectCityPresenter.SelectCityListener {

    @BindView(R.id.citiesListView)
    ListView listView;
    @BindView(R.id.serachEditText)
    EditText editText;
    List<HashMap<String, City>> hashMapList;
    ArrayAdapter<City> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city_acticity);
        ButterKnife.bind(this);
        SelectCityPresenter selectCityPresenter = new SelectCityPresenter(this, this);
        selectCityPresenter.getAllCity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setPresenter() {

    }


    @Override
    public void onSucces(final List<City> cityList) {
        Logs.d("ListLength", String.valueOf(cityList.size()));

        arrayAdapter = new ArrayAdapter<City>(this, android.R.layout.simple_list_item_1, cityList);
        listView.setAdapter(arrayAdapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              SelectCityActicity.this.arrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City city = (City) adapterView.getItemAtPosition(i);
                PreferencesShared.onStoreData(PreferencesSharedKyes.city,city.getName());
                PreferencesShared.onStoreData(PreferencesSharedKyes.latitude,city.getLatitude());
                PreferencesShared.onStoreData(PreferencesSharedKyes.longitude,city.getLongitude());
                onStartActivity(MainActivity.class,true);
            }
        });

    }

    @Override
    public void onFailure() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }
}
