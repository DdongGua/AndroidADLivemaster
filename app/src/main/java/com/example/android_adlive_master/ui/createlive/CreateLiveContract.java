package com.example.android_adlive_master.ui.createlive;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public interface CreateLiveContract {
    interface View{
        void onCreateSuccess();
        void onCreateFailed();
    }
    interface Presenter{
        void createLive(String url,String liveNmae);
    }
}
