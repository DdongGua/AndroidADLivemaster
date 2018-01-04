package com.example.android_adlive_master.ui.editprofile;

import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.engine.TimProfileHelper;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public class EditProfilePresenter implements EditProfileContract.Presenter {
    EditProfileContract.View view;
    EditProfileActivity editProfileActivity;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
        editProfileActivity = (EditProfileActivity) view;

    }

    @Override
    public void getUserInfo() {
        TimProfileHelper.getInstance().getSelfProfile(editProfileActivity, new TimProfileHelper.OnProfileGet() {
            @Override
            public void onGet(AdouTimUserProfile mProfile) {
                view.updateView(mProfile);
            }

            @Override
            public void noGet() {
                view.onGetInfoFailed();
            }
        });


    }

    @Override
    public void onUpdateInfoSuccess() {
        TimProfileHelper.getInstance().resetApplicationProfile(new TimProfileHelper.OnProfileGet() {
            @Override
            public void onGet(AdouTimUserProfile mProfile) {
                view.updateView(mProfile);
            }

            @Override
            public void noGet() {

            }
        });
    }


}

