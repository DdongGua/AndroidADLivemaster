package com.example.android_adlive_master.app;

import android.app.Application;

import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.engine.MessageObservable;
import com.example.android_adlive_master.timcustom.CustomTimProfileInfo;
import com.tencent.TIMManager;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.tencent.livesdk.ILVLiveConfig;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 亮亮 on 2018/1/1.
 */

public class AdouApplication extends Application {
    static AdouApplication application;
    AdouTimUserProfile adouTimUserProfile;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initLivesdk();

    }

    private void initLivesdk() {
        if (MsfSdkUtils.isMainProcess(this)) {    // 仅在主线程初始化
            // 初始化LiveSDK
            ILiveLog.setLogLevel(ILiveLog.TILVBLogLevel.DEBUG);
            ILiveSDK.getInstance().initSdk(this, 1400059677, 21072);
            ILVLiveManager.getInstance().init(new ILVLiveConfig()
                           .setLiveMsgListener(MessageObservable.getInstance()));
//            long type = CustomTimProfileInfo.ALL_BASE_INFO;
//            List<String> customFields=new ArrayList<>();
//            customFields.add(CustomTimProfileInfo.INFO_FANS);
//            customFields.add(CustomTimProfileInfo.INFO_FORK);
//            customFields.add(CustomTimProfileInfo.INFO_GRADE);
//            customFields.add(CustomTimProfileInfo.INFO_RECEIVE);
//            customFields.add(CustomTimProfileInfo.INFO_SEND);
//            customFields.add(CustomTimProfileInfo.INFO_XINGZUO);
//            TIMManager.getInstance().initFriendshipSettings(type,customFields);


        }

    }

    public static AdouApplication getApp() {
        return application;
    }

    public void setAdouTimUserProfile(AdouTimUserProfile profile) {
        if (profile != null) {
            adouTimUserProfile = profile;

        }

    }

    public AdouTimUserProfile getAdouTimUserProfile() {
        return adouTimUserProfile;
    }

}
