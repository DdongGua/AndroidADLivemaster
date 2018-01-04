package com.example.android_adlive_master.utils;

import android.widget.Toast;

import com.example.android_adlive_master.app.AdouApplication;

/**
 * Created by 亮亮 on 2018/1/1.
 */ //Toast吐司工具类

public class ToastUtils {
    static Toast toast;
    public static void show(String str) {
        if (toast == null) {
            toast = Toast.makeText(AdouApplication.getApp().getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(str);
        toast.show();
    }
}
