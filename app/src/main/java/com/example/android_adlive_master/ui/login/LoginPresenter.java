package com.example.android_adlive_master.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.android_adlive_master.engine.TimProfileHelper;
import com.example.android_adlive_master.ui.profile.ProfileActivity;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

/**
 * Created by 亮亮 on 2018/1/1.
 */

public class LoginPresenter implements LoginContract.Presenter {
    //持有view的引用
     LoginContract.View mView;
     LoginActivity loginActivity;


    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        loginActivity=(LoginActivity)mView;
    }

    @Override
    public void login(String account, String pass) {
        if(TextUtils.isEmpty(account)||TextUtils.isEmpty(pass)){
            mView.loginInfoEmpty();
            return;
        }
        if(account.length()<8||pass.length()<8){
            mView.loginInfoError();
            return;
        }
        realLogin(account,pass);

    }

    private void realLogin(final String account, String pass) {

        ILiveLoginManager.getInstance().tlsLoginAll(account, pass, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
               mView.loginSuccess();
                getUserInfo();
                Intent intent= new Intent(loginActivity, ProfileActivity.class);
                loginActivity.startActivity(intent);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

                mView.loginFailed(errCode,errMsg);
            }
        });
    }
    //获取用户的个人信息
       private void getUserInfo() {
        new TimProfileHelper().getSelfProfile(loginActivity,null);
    }


}
