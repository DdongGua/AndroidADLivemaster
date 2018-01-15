package com.example.android_adlive_master.ui.createlive;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.bean.CreateliveInfo;
import com.example.android_adlive_master.bean.HomeInfo;
import com.example.android_adlive_master.bean.HostRoomInfo;
import com.example.android_adlive_master.engine.live.Constants;
import com.example.android_adlive_master.httputil.BaseOnRequestComplete;
import com.example.android_adlive_master.httputil.Constant;
import com.example.android_adlive_master.httputil.OkHttpHelper;
import com.example.android_adlive_master.ui.host.HostLiveActivity;
import com.tencent.TIMUserProfile;

import java.util.HashMap;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public class CreateLivePresenter implements CreateLiveContract.Presenter {
    CreateLiveContract.View mView;
    CreateLiveActivity createLiveActivity;

    public CreateLivePresenter(CreateLiveContract.View mView) {
        this.mView = mView;
        createLiveActivity = (CreateLiveActivity) mView;
    }

    @Override
    public void createLive(String url, String liveName) {
        //创建直播
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(liveName)) {
            mView.onCreateFailed();
        } else {
            //尝试去创建
            /*
             先把封面和房间名称 包括创建者id，创建者昵称，创建者头像 (application有缓存)  传给服务器，返回信息中包含roomid
           */
            requestRoomId(url, liveName);
        }


    }

    //获取房间id
    private void requestRoomId(String cover, String liveName) {
        HashMap<String, String> map = new HashMap<>();
        AdouTimUserProfile profile = AdouApplication.getApp().getAdouTimUserProfile();
        TIMUserProfile profile1 = profile.getProfile();
        //      String nickname = null;
//        String liveName1=null;
//        try {
//            nickname = URLEncoder.encode(profile1.getNickName(),"UTF-8");
//             liveName1 = URLEncoder.encode(liveName,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        map.put("action", "create");     //动作
        map.put("userId", profile1.getIdentifier());  //主播id
        map.put("userAvatar", profile1.getFaceUrl()); //主播头像
        map.put("userName", profile1.getNickName()); //主播昵称
        map.put("liveTitle", liveName); //直播标题
        map.put("liveCover", cover); //直播封面
        OkHttpHelper.getInstance().postObject(Constant.HOST, map, new BaseOnRequestComplete<CreateliveInfo>() {
            @Override
            public void onSuccess(CreateliveInfo info) {
                //当创建直播成功后
                if (info != null) {
                    int roomId = info.getData().getRoomId();
                    if (roomId != 0) {
                        Intent intent = new Intent(createLiveActivity, HostLiveActivity.class);
                        intent.putExtra("roomId", roomId);
                        createLiveActivity.startActivity(intent);
                    }
                } else {
                    //数据为空
                    onEmpty();
                }


            }
        }, CreateliveInfo.class);
    }
}

