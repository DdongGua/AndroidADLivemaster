package com.example.android_adlive_master.ui.host;

import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.engine.live.LiveHelper;
import com.example.android_adlive_master.httputil.BaseOnRequestComplete;
import com.example.android_adlive_master.httputil.Constant;
import com.example.android_adlive_master.httputil.OkHttpHelper;
import com.tencent.TIMUserProfile;

import java.util.HashMap;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public class HostLivePresenter implements HostLiveContract.Presenter {
    private HostLiveContract.View view;
    HostLiveActivity hostLiveActivity;

    public HostLivePresenter(HostLiveContract.View view) {
        this.view = view;
        hostLiveActivity = (HostLiveActivity) view;
    }

    @Override
    public void createHost(int roomId) {
        LiveHelper.getInstance(hostLiveActivity).createRoom(roomId + "");

    }

    @Override
    public void quitHost(int roomId) {
        //获取userId 和房间id，然后退出
        TIMUserProfile profile = AdouApplication.getApp().getAdouTimUserProfile().getProfile();
        String userId = profile.getIdentifier();
        HashMap<String, String> map = new HashMap<>();
        map.put("action", "quit");
        map.put("userId", userId);
        map.put("roomId", roomId + "");
        OkHttpHelper.getInstance().postString(Constant.HOST, map, new BaseOnRequestComplete<String>() {
            @Override
            public void onSuccess(String s) {
                hostLiveActivity.finish();
            }
        });

    }
}
