package com.example.android_adlive_master.ui.login;

/**
 * Created by 亮亮 on 2018/1/1.
 */

public interface LoginContract {
    interface View {
        //登陆成功
        void loginSuccess();

        //登陆失败
        void loginFailed(int errCode, String errMsg);

        //登录信息为空
        void loginInfoEmpty();

        //登录信息出错
        void loginInfoError();
    }

    interface Presenter {
        void login(String account, String pass);

    }
}
