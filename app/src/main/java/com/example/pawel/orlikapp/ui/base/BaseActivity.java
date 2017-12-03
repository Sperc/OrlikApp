package com.example.pawel.orlikapp.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

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
    public void onStartActivity(Class w,boolean finishThisActivity){
        Intent intent = new Intent(this,w);
        startActivity(intent);
        if(finishThisActivity){
            finish();
        }
    }
    public abstract void initialize();
    public abstract void onButtonClick();
    public abstract void setPresenter();
}
