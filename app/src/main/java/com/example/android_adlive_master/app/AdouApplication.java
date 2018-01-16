package com.example.android_adlive_master.app;

import android.app.Application;

import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.engine.MessageObservable;
import com.example.android_adlive_master.qiniu.QiniuUploadHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.tencent.livesdk.ILVLiveConfig;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

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
        // 初始化LiveSDK
        initLivesdk();
        //初始化七牛sdk
        initQiniuSdk();
        //初始化logger工具类
        initLogger();


    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                       .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                       .methodCount(0)         // (Optional) How many method line to show. Default 2
                       .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                       .tag("GUAJU")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                       .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

    }

    private void initQiniuSdk() {
        QiniuUploadHelper.init(QiniuConfig.SPACENAME, QiniuConfig.SecretKey, QiniuConfig.AccessKey);
    }

    private void initLivesdk() {
        if (MsfSdkUtils.isMainProcess(this)) {    // 仅在主线程初始化

            ILiveLog.setLogLevel(ILiveLog.TILVBLogLevel.DEBUG);
            //me
            ILiveSDK.getInstance().initSdk(this, 1400059677, 21072);
            //guaju
            ILiveSDK.getInstance().initSdk(this, 1400059239, 21019);
            ILVLiveManager.getInstance().init(new ILVLiveConfig()
                           .setLiveMsgListener(MessageObservable.getInstance()));
//              //初始化自定义资料信息
//            long type = CustomTimConstant.ALL_BASE_INFO;
//            List<String> customFields=new ArrayList<>();
//            customFields.add(CustomTimConstant.INFO_FANS);
//            customFields.add(CustomTimConstant.INFO_FORK);
//            customFields.add(CustomTimConstant.INFO_GRADE);
//            customFields.add(CustomTimConstant.INFO_RECEIVE);
//            customFields.add(CustomTimConstant.INFO_SEND);
//            customFields.add(CustomTimConstant.INFO_XINGZUO);
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
