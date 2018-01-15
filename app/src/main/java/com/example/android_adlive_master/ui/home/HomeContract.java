package com.example.android_adlive_master.ui.home;

import com.example.android_adlive_master.adapter.HomeRoomInfoAdapter;

/**
 * Created by 亮亮 on 2018/1/13.
 */

public interface HomeContract {
    interface View{
        void setHomeRoomInfoAdapter(HomeRoomInfoAdapter adapter);
        void updataHomeRoomInfoAdapter(HomeRoomInfoAdapter adapter);
    }
    interface Presenter{
        void getHomeLiveList(int page);
    }
}
