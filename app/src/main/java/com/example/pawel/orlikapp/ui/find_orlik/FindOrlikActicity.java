package com.example.pawel.orlikapp.ui.find_orlik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class FindOrlikActicity extends BaseActivity implements FindOrlikActivityInterface {

    private Spinner selectCitySpiner;
    private String[] array = {"Lublin","Warszawa","Krakow"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_orlik);
        initialize();
        addAdapter();
    }

    @Override
    public void initialize() {
        selectCitySpiner = (Spinner)findViewById(R.id.selctCity);
    }

    @Override
    public void addAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCitySpiner.setAdapter(adapter);
     }
}
