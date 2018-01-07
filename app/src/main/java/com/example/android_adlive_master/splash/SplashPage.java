package com.example.android_adlive_master.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.ui.login.LoginActivity;

/**
 * Created by 亮亮 on 2017/12/31.
 *///3秒跳转页面

public class SplashPage extends Activity {
    private Handler handler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(SplashPage.this,LoginActivity.class);
                startActivity(in);
                finish();
            }
        },3000);
    }
}
