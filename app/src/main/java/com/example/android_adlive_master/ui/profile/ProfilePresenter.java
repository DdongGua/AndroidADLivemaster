package com.example.android_adlive_master.ui.profile;


import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.engine.TimProfileHelper;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public class ProfilePresenter implements ProfileContract.Presenter{


    ProfileContract.View view;
    ProfileActivity activity;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        activity=(ProfileActivity)view;
    }

    @Override
    public void getUserProfile() {
            new TimProfileHelper().getSelfProfile(activity, new TimProfileHelper.OnProfileGet() {
                @Override
                public void onGet(AdouTimUserProfile mProfile) {
                    view.updateProfile(mProfile);
                }

                @Override
                public void noGet() {
                    //没有获取到
                    view.updateProfileError();

                }
            });
        }
    }
