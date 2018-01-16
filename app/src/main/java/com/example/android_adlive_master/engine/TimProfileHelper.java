package com.example.android_adlive_master.engine;

import android.app.Activity;

import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;

/**
 * Created by 亮亮 on 2018/1/3.
 */

public class TimProfileHelper {
    private static final String TAG = "TimProfileHelper";
    private static TimProfileHelper helper;

    public static TimProfileHelper getInstance() {
        if (helper == null) {
            helper = new TimProfileHelper();
        }
        return helper;
    }


    public interface OnProfileGet {
        void onGet(AdouTimUserProfile mProfile);

        void noGet();
    }

    //拿到个人信息
    public void getSelfProfile(final Activity activity, final OnProfileGet onProfileGet) {
        //先从app中拿
//        AdouTimUserProfile adouTimUserProfile = AdouApplication.getApp().getAdouTimUserProfile();
//        if (adouTimUserProfile != null) {
//            onProfileGet.onGet(adouTimUserProfile);
//
//            return;
//        }
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
                if (onProfileGet != null)
                    onProfileGet.noGet();
            }

            @Override
            public void onSuccess(TIMUserProfile profile) {
                AdouTimUserProfile mProfile = new AdouTimUserProfile();
                //设置整个profile
                if (profile != null) {
                    mProfile.setProfile(profile);
                }
//                //附加信息
//                Map<String, byte[]> customInfo = profile.getCustomInfo();
//                if (customInfo!=null&&!customInfo.isEmpty()) {
//
//                    if (customInfo.containsKey(CustomTimConstant.INFO_GRADE)) {
//                        byte[] gradeBytes = customInfo.get(CustomTimConstant.INFO_GRADE);
//                        if (gradeBytes != null) {
//                            //设置等级
//                            mProfile.setGrade(Integer.parseInt(new String(gradeBytes)));
//                        }
//                    }
//                    if (customInfo.containsKey(CustomTimConstant.INFO_RECEIVE)) {
//                        byte[] receiveBytes = customInfo.get(CustomTimConstant.INFO_RECEIVE);
//                        if (receiveBytes != null) {
//                            //设置接收的礼物
//                            mProfile.setReceive(Integer.parseInt(new String(receiveBytes)));
//                        }
//                    }
//                    if (customInfo.containsKey(CustomTimConstant.INFO_SEND)) {
//                        byte[] sendBytes = customInfo.get(CustomTimConstant.INFO_SEND);
//                        if (sendBytes != null) {
//                            //设置发送出去的礼物
//                            mProfile.setSend(Integer.parseInt(new String(sendBytes)));
//                        }
//                    }
//                    if (customInfo.containsKey(CustomTimConstant.INFO_XINGZUO)) {
//                        byte[] xingzuoBytes = customInfo.get(CustomTimConstant.INFO_XINGZUO);
//                        if (xingzuoBytes != null) {
//                            //设置星座
//                            mProfile.setXingzuo(new String(xingzuoBytes));
//                        }
//                    }
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
//                //附加信息
//                Map<String, byte[]> customInfo = profile.getCustomInfo();
//                if (customInfo!=null&&customInfo.size() > 0) {
//
//                    if (customInfo.containsKey(CustomTimConstant.INFO_GRADE)) {
//                        byte[] gradeBytes = customInfo.get(CustomTimConstant.INFO_GRADE);
//                        if (gradeBytes != null) {
//                            mProfile.setGrade(Integer.parseInt(new String(gradeBytes)));
//                        }
//                    }
//                    if (customInfo.containsKey(CustomTimConstant.INFO_RECEIVE)) {
//                        byte[] receiveBytes = customInfo.get(CustomTimConstant.INFO_RECEIVE);
//                        if (receiveBytes != null) {
//                            mProfile.setReceive(Integer.parseInt(new String(receiveBytes)));
//                        }
//                    }
//                    if (customInfo.containsKey(CustomTimConstant.INFO_SEND)) {
//                        byte[] sendBytes = customInfo.get(CustomTimConstant.INFO_SEND);
//                        if (sendBytes != null) {
//                            mProfile.setSend(Integer.parseInt(new String(sendBytes)));
//                        }
//                    }
//                    if (customInfo.containsKey(CustomTimConstant.INFO_XINGZUO)) {
//                        byte[] xingzuoBytes = customInfo.get(CustomTimConstant.INFO_XINGZUO);
//                        if (xingzuoBytes != null) {
//                            mProfile.setXingzuo(new String(xingzuoBytes));
//
//                        }
//                    }
//                }
                AdouApplication.getApp().setAdouTimUserProfile(mProfile);
            }
        });
    }


}
