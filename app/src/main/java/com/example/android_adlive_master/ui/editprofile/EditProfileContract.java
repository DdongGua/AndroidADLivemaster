package com.example.android_adlive_master.ui.editprofile;

import com.example.android_adlive_master.bean.AdouTimUserProfile;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public interface EditProfileContract {
    interface  View{
        //更新界面
        void updateView(AdouTimUserProfile profile);
        //更新失败
        void onGetInfoFailed();
        //更新出错
        void updateInfoError();
        //更新成功
        void updateInfoSuccess();

    }
    interface  Presenter{
        //拿到个人信息
        void getUserInfo();
        //更新信息成功
        void onUpdateInfoSuccess();
    }
}
