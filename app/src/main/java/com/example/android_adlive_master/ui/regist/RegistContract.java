package com.example.android_adlive_master.ui.regist;

/**
 * Created by 亮亮 on 2018/1/1.
 */

public interface RegistContract {
    interface View{
        //注册成功
        void registSuccess();
        //注册失败
        void registError();
        //注册信息为空
        void registInfoEmpty();
        //注册信息有误
        void registInfoError();
        //两次密码不一致
        void registConfirmPassError();

    }
    interface Presenter{
        void regist(String account,String pass,String confirmPass);

    }
}
