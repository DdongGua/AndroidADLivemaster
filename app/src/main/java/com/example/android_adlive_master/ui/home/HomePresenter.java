package com.example.android_adlive_master.ui.home;

import android.content.Intent;

import com.example.android_adlive_master.adapter.HomeRoomInfoAdapter;
import com.example.android_adlive_master.bean.AllHomeInfo;
import com.example.android_adlive_master.bean.CreateliveInfo;
import com.example.android_adlive_master.httputil.BaseOnRequestComplete;
import com.example.android_adlive_master.httputil.Constant;
import com.example.android_adlive_master.httputil.OkHttpHelper;
import com.example.android_adlive_master.ui.watch.WatchLiveActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 亮亮 on 2018/1/13.
 */

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View view;
    HomeFragment homeFragment;
    private ArrayList<AllHomeInfo.DataBean> data;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        homeFragment = (HomeFragment) view;

    }

    @Override
    public void getHomeLiveList(final int page) {
        String url = Constant.HOST;
        HashMap<String, String> map = new HashMap<>();
        map.put("action", "getList");
        map.put("pageIndex", page + "");

        //获取首页直播列表
        OkHttpHelper.getInstance().postObject(url, map, new BaseOnRequestComplete<AllHomeInfo>() {
            private HomeRoomInfoAdapter homeRoomInfoAdapter;

            @Override
            public void onSuccess(AllHomeInfo baseBean) {
                Logger.e(baseBean.getCode());
                if (baseBean.getData() != null) {
                    //第一次获取数据时直接赋值给list
                    data = baseBean.getData();
                    homeRoomInfoAdapter = new HomeRoomInfoAdapter(homeFragment.getActivity(), data);
                    homeRoomInfoAdapter.setOnMyClickListener(new HomeRoomInfoAdapter.OnMyClickListener() {
                        @Override
                        public void onMyClick(AllHomeInfo.DataBean bean) {
                            //做相应的操作：跳转到直播页面 观看直播
                            Intent intent = new Intent(homeFragment.getActivity(), WatchLiveActivity.class);
                            intent.putExtra("roomId", bean.getRoomId());
                            intent.putExtra("hostId", bean.getUserId());
                            homeFragment.getActivity().startActivity(intent);
                        }
                    });
                    view.setHomeRoomInfoAdapter(homeRoomInfoAdapter);
                } else {
                    //报数据为空
                    onEmpty();


                }
                //停止刷新
                homeFragment.srl.setRefreshing(false);

            }

            @Override
            public void onEmpty() {
                super.onEmpty();
                //停止刷新
                homeFragment.srl.setRefreshing(false);
            }

            @Override
            public void onError() {
                super.onError();
                //停止刷新
                homeFragment.srl.setRefreshing(false);
            }

            @Override
            public void onFailed() {
                super.onFailed();
                //停止刷新
                homeFragment.srl.setRefreshing(false);
            }
        }, AllHomeInfo.class);

    }


}
