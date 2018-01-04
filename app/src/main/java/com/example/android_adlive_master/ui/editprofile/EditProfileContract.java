package com.example.android_adlive_master.ui.editprofile;

import com.example.android_adlive_master.bean.AdouTimUserProfile;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public interface EditProfileContract {
    interface  View{
        void updateView(AdouTimUserProfile profile);
        void onGetInfoFailed();
        void updateInfoError();
        void updateInfoSuccess();

    }
    interface  Presenter{
        void getUserInfo();
        void onUpdateInfoSuccess();
    }
}
