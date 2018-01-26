package com.example.android_adlive_master.widget.gift;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.bean.GiftMsgInfo;
import com.example.android_adlive_master.utils.ImageUtils;

import java.util.LinkedList;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * Created by 亮亮 on 2018/1/25.
 */

public class GiftFullScreenView extends FrameLayout {
    LinkedList<GiftMsgInfo> msgs=new LinkedList<>();
    private LayoutInflater inflater;
    private WindowManager windowManager;
    private int screenWidth;
    private int screenHeight;
    private ImageView iv_sender;
    private TextView tv_sender_id;
    private GiftFullScreenItem item;

    public GiftFullScreenView(@NonNull Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init();

    }

    public GiftFullScreenView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();

    }
    public void init(){
        View view = inflater.inflate(R.layout.gift_full_screen_view, this, true);
        windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        screenWidth=defaultDisplay.getWidth();
        screenHeight=defaultDisplay.getHeight();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth,screenHeight);
        view.setLayoutParams(layoutParams);

        //占满全屏
        iv_sender = view.findViewById(R.id.iv_sender);
        tv_sender_id = view.findViewById(R.id.tv_sender_id);
        view.setBackgroundColor(getResources().getColor(R.color.transprant));
        item = view.findViewById(R.id.giftfullscreenitem);
        item.setVisibility(View.INVISIBLE);
        //给item提供当动画完成的接口
        item.setOnGiftAnimationComleted(new GiftFullScreenItem.OnGiftAnimationCompleted() {
            @Override
            public void onCompleted() {
                //把自己设置为隐藏
                setVisibility(View.INVISIBLE);
                //取消息展示
                if(!msgs.isEmpty()){
                    GiftMsgInfo giftMsgInfo = msgs.removeFirst();
                    //礼物item可用
                    bindData(giftMsgInfo);
                    setVisibility(View.VISIBLE);
                    //开始动画
                    item.porcheGo();
                }
            }
        });


    }
    //获得可用的条目
    public GiftFullScreenItem getAvaliableItem(){
        if(item.getVisibility()==View.INVISIBLE){
            return item;
        }else {
            return null;
        }
    }
    //外界调用展示礼物的方法
    public void showFullScreenGift(GiftMsgInfo msgInfo){
        //先设置自己显示出来
        GiftFullScreenItem avaliableItem = getAvaliableItem();
        if(avaliableItem!=null){
            //礼物item可用
            bindData(msgInfo);
            //开始动画
            setVisibility(View.VISIBLE);
            avaliableItem.porcheGo();
        }else {
            //正在动画当中，需要缓存
            msgs.add(msgInfo);
        }

    }
    //提供绑定信息的方法
    public void bindData(GiftMsgInfo msgInfo) {
        if(msgInfo!=null){
            if(!TextUtils.isEmpty(msgInfo.getAvatar())){
                ImageUtils.getInstance().loadCircle(msgInfo.getAvatar(),iv_sender);
            }else {
                ImageUtils.getInstance().loadCircle(R.mipmap.splash_anima,iv_sender);
            }
            if(!TextUtils.isEmpty(msgInfo.getAdouID())){
                tv_sender_id.setText(msgInfo.getAdouID());
            }else {
                tv_sender_id.setText("阿斗号");
            }
        }
    }
}
