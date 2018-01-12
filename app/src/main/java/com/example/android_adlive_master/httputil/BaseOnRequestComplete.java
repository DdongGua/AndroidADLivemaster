package com.example.android_adlive_master.httputil;

import com.example.android_adlive_master.utils.ToastUtils;

/**
 * Created by 亮亮 on 2018/1/11.
 */

public abstract class BaseOnRequestComplete<T> implements OkHttpHelper.OnRequestComplete<T> {
    @Override
    public void onEmpty() {
        ToastUtils.show("数据为空");
    }
    @Override
    public void onError() {
        ToastUtils.show("访问数据失败，请稍后重试");
    }
    @Override
    public void onFailed() {
        ToastUtils.show("请检查您的网络（服务器异常）");
    }

    @Override
    public void onDataComplete() {
        ToastUtils.show("数据已加载完毕");

    }
}
