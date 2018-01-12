package com.example.android_adlive_master.ui.profile;


import android.util.Log;

import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.engine.TimProfileHelper;
import com.example.android_adlive_master.ui.mine.MineFragment;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public class ProfilePresenter implements ProfileContract.Presenter{

    private static final String TAG = "ProfilePresenter";
    ProfileContract.View view;
    MineFragment mineFragment;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        mineFragment=(MineFragment)view;
    }

    @Override
    public void getUserProfile() {
            new TimProfileHelper().getSelfProfile(mineFragment.getActivity(), new TimProfileHelper.OnProfileGet() {
                @Override
                public void onGet(AdouTimUserProfile mProfile) {
                    view.updateProfile(mProfile);
                }

                @Override
                public void noGet() {
                    //没有获取到
                    Log.e(TAG, "noGet: 没有获取到++" );
                    view.updateProfileError();

                }
            });
        }
    }
