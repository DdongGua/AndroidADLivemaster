package com.example.android_adlive_master.ui.profile;

import com.example.android_adlive_master.bean.AdouTimUserProfile;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public interface ProfileContract {
    interface View{
        void updateProfile(AdouTimUserProfile profile);
        void updateProfileError();
    }
    interface Presenter{
        void getUserProfile();
    }
}
