package com.example.android_adlive_master.ui.host;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public interface HostLiveContract {
    interface View{


    }
    interface Presenter{
        void createHost(int roomId);
//        //退出房间
//        void quitHost(int roomId);
    }
}
