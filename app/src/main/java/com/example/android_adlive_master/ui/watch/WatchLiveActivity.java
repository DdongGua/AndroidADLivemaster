package com.example.android_adlive_master.ui.watch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.engine.live.Constants;
import com.example.android_adlive_master.engine.live.DemoFunc;
import com.example.android_adlive_master.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.livesdk.ILVLiveRoomOption;

/**
 * Created by 亮亮 on 2018/1/12.
 */

public class WatchLiveActivity extends Activity {

    private AVRootView av_rootview;
    private int roomId;
    private String hostId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_live);
        initView();
        initRootView();
        //获取房间号，和主播号
        getinfoAndJoinRoom();
    }

    private void getinfoAndJoinRoom() {
        Intent intent = getIntent();
        if (intent!=null){
            roomId = intent.getIntExtra("roomId",-1);
            hostId = intent.getStringExtra("hostId");
            joinRoom(roomId+"");
        }
    }



    private void initView() {
        av_rootview = findViewById(R.id.av_rootview);
    }

    private void initRootView() {
        ILVLiveManager.getInstance().setAvVideoView(av_rootview);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ILVLiveManager.getInstance().onPause();
        Logger.e("onpause观看者");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ILVLiveManager.getInstance().onResume();
        Logger.e("onresume观看者");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ILVLiveManager.getInstance().onDestory();
        Logger.e("ondestoy观看者");
    }
    // 加入房间
    private void joinRoom(String roomString){
        int roomId = DemoFunc.getIntValue(roomString, -1);
        if (-1 == roomId){
            ToastUtils.show("房间号不合法");
            //退出房间
            finish();
            return;
        }
        ILVLiveRoomOption option = new ILVLiveRoomOption("")
                       .controlRole(Constants.ROLE_GUEST) //是一个浏览者
                       .videoMode(ILiveConstants.VIDEOMODE_NORMAL)
                       .autoCamera(false)
                       .autoMic(false);
        ILVLiveManager.getInstance().joinRoom(roomId,
                       option, new ILiveCallBack() {
                           @Override
                           public void onSuccess(Object data) {
                               //成功的时候怎么办
                               ToastUtils.show("哈哈哈哈哈，快看我！");

                           }
                           @Override
                           public void onError(String module, int errCode, String errMsg) {
                               ToastUtils.show("加入房间失败，正在退出。。。");
                               //退出房间
                               finish();
                           }
                       });
    }

}
