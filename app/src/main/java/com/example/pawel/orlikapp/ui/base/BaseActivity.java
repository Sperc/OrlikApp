package com.example.pawel.orlikapp.ui.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.engine.ServiceGenerator;
import com.example.pawel.orlikapp.model.MyObject;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.engine.httpApi.PlaygroundClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {
    String API_BASE_URL = "http://192.168.0.185:8080/";
    private Button button;
    private TextView textView;
    private PlaygroundClient playgroundClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
     //   myObjecs();
    }

    private  void myObjecs(){
        playgroundClient = ServiceGenerator.createService().create(PlaygroundClient.class);
        Call<MyObject> call = playgroundClient.getObj();
        call.enqueue(new Callback<MyObject>() {
            @Override
            public void onResponse(Call<MyObject> call, Response<MyObject> response) {
                MyObject w = response.body();
                textView.setText(w.getName());
                Toast.makeText(getApplicationContext(),w.getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MyObject> call, Throwable t) {
                //Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_LONG).show();
                textView.setText(t.getMessage());
            }
        });
    }
    private void playground(){
        playgroundClient = ServiceGenerator.createService().create(PlaygroundClient.class);
        Call<List<Playground>> call = playgroundClient.getAllPlaygrounds();
        call.enqueue(new Callback<List<Playground>>() {
            @Override
            public void onResponse(Call<List<Playground>> call, Response<List<Playground>> response) {
                List<Playground> res = response.body();
                int w = res.get(0).getBookingSet().size();
                backgroundThreadShortToast(BaseActivity.this, String.valueOf(res.size()));
            }

            @Override
            public void onFailure(Call<List<Playground>> call, Throwable t) {
                Toast.makeText(BaseActivity.this,"dasasdasd",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void backgroundThreadShortToast(final Context context,
                                                  final String msg) {
        if (context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void storeTokenAndUserToSharePrefs(String token,String username){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("token", token);
        editor.apply();
    }
    public String readDataFromPrefs(String key){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }


}
