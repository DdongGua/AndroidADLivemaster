package com.example.android_adlive_master.ui.host;

import com.example.android_adlive_master.engine.live.LiveHelper;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public class HostLivePresenter implements HostLiveContract.Presenter {
    private HostLiveContract.View view;
    HostLiveActivity hostLiveActivity;

    public HostLivePresenter(HostLiveContract.View view) {
        this.view = view;
        hostLiveActivity=(HostLiveActivity)view;
    }

    @Override
    public void createHost() {
        LiveHelper.getInstance(hostLiveActivity).createRoom("00000000");

    }
}
