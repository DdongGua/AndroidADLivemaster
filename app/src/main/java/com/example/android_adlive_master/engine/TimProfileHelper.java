package com.example.android_adlive_master.engine;

import android.app.Activity;
import android.util.Log;

import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.timcustom.CustomTimProfileInfo;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;

import java.util.Map;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public class TimProfileHelper {
    private static final String TAG = "TimProfileHelper";
    private static TimProfileHelper helper = new TimProfileHelper();

    public static TimProfileHelper getInstance() {
        return helper;
    }


    public interface OnProfileGet {
        void onGet(AdouTimUserProfile mProfile);

        void noGet();
    }

    //拿到个人信息
    public void getSelfProfile(final Activity activity, final OnProfileGet onProfileGet) {
        //先从app中拿
        AdouTimUserProfile adouTimUserProfile = AdouApplication.getApp().getAdouTimUserProfile();
        if (adouTimUserProfile != null) {
            Log.e(TAG, "getSelfProfile: 11111111111");
            onProfileGet.onGet(adouTimUserProfile);

            return;
        }
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
                if (onProfileGet != null)
                    onProfileGet.noGet();
                Log.e(TAG, "onError: hahhahhaha");
            }

            @Override
            public void onSuccess(TIMUserProfile profile) {
                AdouTimUserProfile mProfile = new AdouTimUserProfile();
                //设置整个profile
                if (profile != null) {
                    mProfile.setProfile(profile);
                }

//                Map<String, byte[]> customInfo = profile.getCustomInfo();
//                byte[] gradeBytes = customInfo.get(CustomTimProfileInfo.INFO_GRADE);
//                byte[] receiveBytes = customInfo.get(CustomTimProfileInfo.INFO_RECEIVE);
//                byte[] sendBytes = customInfo.get(CustomTimProfileInfo.INFO_SEND);
//                byte[] xingzuoBytes = customInfo.get(CustomTimProfileInfo.INFO_XINGZUO);
//                if (gradeBytes != null) {
//                    mProfile.setGrade(Integer.parseInt(new String(gradeBytes)));
//                }
//                if (receiveBytes != null) {
//                    mProfile.setReceive(Integer.parseInt(new String(receiveBytes)));
//                }
//                if (sendBytes != null) {
//                    mProfile.setSend(Integer.parseInt(new String(sendBytes)));
//                }
//                if (xingzuoBytes != null) {
//                    mProfile.setXingzuo(new String(xingzuoBytes));
//                }
                AdouApplication.getApp().setAdouTimUserProfile(mProfile);
                if (onProfileGet != null)
                    onProfileGet.onGet(mProfile);
            }

        });
    }

    //重置应用中保存的信息
    public void resetApplicationProfile(final OnProfileGet onProfileGet) {
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
                onProfileGet.noGet();
            }

            @Override
            public void onSuccess(TIMUserProfile profile) {
                AdouTimUserProfile mProfile = new AdouTimUserProfile();
                //设置整个profile
                if (profile != null) {
                    mProfile.setProfile(profile);
                    onProfileGet.onGet(mProfile);
                }
                //附加信息
//            Map<String, byte[]> customInfo = profile.getCustomInfo();
//            byte[] gradeBytes = customInfo.get(CustomTimProfileInfo.INFO_GRADE);
//            byte[] receiveBytes = customInfo.get(CustomTimProfileInfo.INFO_RECEIVE);
//            byte[] sendBytes = customInfo.get(CustomTimProfileInfo.INFO_SEND);
//            byte[] xingzuoBytes = customInfo.get(CustomTimProfileInfo.INFO_XINGZUO);
//            if (gradeBytes!=null){
//                mProfile.setGrade(Integer.parseInt(new String(gradeBytes)));
//            }
//            if (receiveBytes!=null){
//                mProfile.setReceive(Integer.parseInt(new String(receiveBytes)));
//            }
//            if (sendBytes!=null){
//                mProfile.setSend(Integer.parseInt(new String(sendBytes)));
//            }
//            if (xingzuoBytes!=null){
//                mProfile.setXingzuo(new String(xingzuoBytes));
//            }
                AdouApplication.getApp().setAdouTimUserProfile(mProfile);
            }

        });
    }


}
