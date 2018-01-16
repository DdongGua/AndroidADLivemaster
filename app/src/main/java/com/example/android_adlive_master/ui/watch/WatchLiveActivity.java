package com.example.android_adlive_master.ui.watch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.app.AdouApplication;
import com.example.android_adlive_master.bean.TextMsgInfo;
import com.example.android_adlive_master.engine.MessageObservable;
import com.example.android_adlive_master.engine.live.Constants;
import com.example.android_adlive_master.engine.live.DemoFunc;
import com.example.android_adlive_master.timcustom.CustomTimConstant;
import com.example.android_adlive_master.utils.ToastUtils;
import com.example.android_adlive_master.widget.BottomChatSwitchLayout;
import com.example.android_adlive_master.widget.BottomSwitchLayout;
import com.example.android_adlive_master.widget.HeightSensenableRelativeLayout;
import com.example.android_adlive_master.widget.LiveMsgListView;
import com.orhanobut.logger.Logger;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMMessage;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.livesdk.ILVCustomCmd;
import com.tencent.livesdk.ILVLiveConfig;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.livesdk.ILVLiveRoomOption;
import com.tencent.livesdk.ILVText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 亮亮 on 2018/1/12.
 */

public class WatchLiveActivity extends Activity implements ILVLiveConfig.ILVLiveMsgListener{

    private AVRootView av_rootview;
    private int roomId;
    private String hostId;
    private BottomSwitchLayout bottomswitchlayout;
    private BottomChatSwitchLayout chatswitchlayout;
    private HeightSensenableRelativeLayout heightsrl;
    private LiveMsgListView lmlv;
    //创建集合专门存储消息
    private ArrayList<TextMsgInfo> mList=new ArrayList<TextMsgInfo>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_live);
        //初始化消息的接受者
        MessageObservable.getInstance().addObserver(this);
        initView();
        initRootView();
        lmlv.setData(mList);
        //获取房间号，和主播号
        getinfoAndJoinRoom();
        setListener();
    }
    private void setDefultStatus() {
        chatswitchlayout.setVisibility(View.INVISIBLE);
        bottomswitchlayout.setVisibility(View.VISIBLE);
    }

    private void setListener() {
        heightsrl.setOnLayoutHeightChangedListenser(new HeightSensenableRelativeLayout.OnLayoutHeightChangedListenser() {
            @Override
            public void showNormal() {
                setDefultStatus();
            }

            @Override
            public void showChat() {
                chatswitchlayout.setVisibility(View.VISIBLE);
                bottomswitchlayout.setVisibility(View.INVISIBLE);

            }
        });
        bottomswitchlayout.setOnSwitchListener(new BottomSwitchLayout.OnSwitchListener() {
            @Override
            public void onChat() {
                //聊天
                chatswitchlayout.setVisibility(View.VISIBLE);
                bottomswitchlayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onClose() {
                finish();
            }
        });
        chatswitchlayout.setOnMsgListener(new BottomChatSwitchLayout.OnMsgSendListener() {
            @Override
            public void sendMsg(String text) {
                //发送消息
                sendTextMsg(text);
            }

            @Override
            public void danmu(String text) {

            }
        });

    }
    //腾讯云发送普通消息
    private void sendTextMsg(final String text) {
        //通过对方id获取对方的等级和对方的昵称
        List<String> ids = new ArrayList<>();
        ids.add(hostId);
        TIMFriendshipManager.getInstance().getFriendsProfile(ids, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                realSend(timUserProfiles,text);
            }
        });
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
        bottomswitchlayout = findViewById(R.id.bottomswitchlayout);
        chatswitchlayout = findViewById(R.id.chatswitchlayout);
        heightsrl = findViewById(R.id.heightsrl);
        lmlv = findViewById(R.id.lmlv);
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
                               ToastUtils.show("我在直播，快来看我！");

                           }
                           @Override
                           public void onError(String module, int errCode, String errMsg) {
                               ToastUtils.show("加入房间失败，正在退出。。。");
                               //退出房间
                               finish();
                           }
                       });
    }
    //真正的发送消息
    private void realSend(List<TIMUserProfile> timUserProfiles, final String text) {
        //因为获取信息的时候 只传入了只有一个元素的集合，所以到这只能拿到一个用户的信息
        final TIMUserProfile profile = timUserProfiles.get(0);

        //发送普通消息
        ILVLiveManager.getInstance().sendText(new ILVText(ILVText.ILVTextType.eGroupMsg, profile.getIdentifier(), text), new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                String grade;
                //发送成功之后，加入到listview中去
                TextMsgInfo textMsgInfo = new TextMsgInfo();
                byte[] bytes = profile.getCustomInfo().get(CustomTimConstant.INFO_GRADE);
                if (bytes!=null){
                    grade = new String(bytes);
                }else{
                    grade="0";
                }
                String identifier = AdouApplication.getApp().getAdouTimUserProfile().getProfile().getIdentifier();
                textMsgInfo.setAdouID(identifier);
                textMsgInfo.setGrade(Integer.parseInt(grade));
                textMsgInfo.setText(text);
                textMsgInfo.setNickname(profile.getNickName());
                //更新列表
                lmlv.addMsg(textMsgInfo);


            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtils.show("发送失败，错误信息"+errMsg+"错误码"+errCode);
            }
        });
    }


    @Override
    public void onNewTextMsg(ILVText text, String SenderId, TIMUserProfile userProfile) {
        //当接受到普通消息的时候，展示到listview上边去
        String msg=text.getText();
        String nickName = userProfile.getNickName();
        String grade;
        byte[] bytes = userProfile.getCustomInfo().get(CustomTimConstant.INFO_GRADE);
        if (bytes!=null){
            grade = new String(bytes);
        }else{
            grade="0";
        }
        TextMsgInfo textMsgInfo = new TextMsgInfo(Integer.parseInt(grade), nickName, msg, SenderId);
        lmlv.addMsg(textMsgInfo);
    }

    @Override
    public void onNewCustomMsg(ILVCustomCmd cmd, String id, TIMUserProfile userProfile) {

    }

    @Override
    public void onNewOtherMsg(TIMMessage message) {

    }
}
