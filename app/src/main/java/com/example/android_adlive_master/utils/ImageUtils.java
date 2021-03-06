package com.example.android_adlive_master.utils;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_adlive_master.app.AdouApplication;

/**
 * Created by 亮亮 on 2018/1/3.
 */ //Glide加载图片的工具类

public class ImageUtils {
    static ImageUtils imageUtils = new ImageUtils();

    public static ImageUtils getInstance() {
        return imageUtils;
    }

    public void loadCircle(int resid, ImageView iv) {
        Glide.with(AdouApplication.getApp()).
                       load(resid).apply(RequestOptions.circleCropTransform())
                       .into(iv);
    }
    public  void loadCircle(Uri resid, ImageView iv) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(AdouApplication.getApp())
                       .load(resid)
                       .apply(requestOptions)
                       .into(iv);
    }

    public void loadCircle(String path, ImageView iv) {
        Glide.with(AdouApplication.getApp()).
                       load(path).apply(RequestOptions.circleCropTransform())
                       .into(iv);
    }
    public void load(String url,ImageView iv){
        RequestOptions options=new RequestOptions();
        //中心裁剪样式
        options.centerCrop();
        Glide.with(AdouApplication.getApp())
                       .load(url)
                       .apply(options)
                       .into(iv);
    }
    public void load(Uri uri,ImageView iv){
        Glide.with(AdouApplication.getApp())
                       .load(uri)
                       .into(iv);
    }
    public void load(int resId,ImageView iv){
        Glide.with(AdouApplication.getApp())
                       .load(resId)
                       .into(iv);
    }
}
