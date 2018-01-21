package com.example.android_adlive_master.widget.gift;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.bean.GiftMsgInfo;
import com.example.android_adlive_master.utils.ImageUtils;

/**
 * Created by 亮亮 on 2018/1/20.
 */

public class GiftItem extends FrameLayout {

    private LayoutInflater inflater;
    private RelativeLayout rl_gift_mormal;
    private ImageView iv_sender;
    private TextView tv_sender_id;
    private TextView tv_gift_info;
    private ImageView iv_gift_icon;
    private TextView tv_gift_num;
    private Animation gift_left_in;
    private Animation gift_icon_left_in;
    private Animation gift_num_scale;

    public GiftItem(@NonNull Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init();
    }

    public GiftItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
    }
    public void init(){
        View view = inflater.inflate(R.layout.item_send_gift, this, true);
        rl_gift_mormal = view.findViewById(R.id.rl_gift_normal);
        iv_sender = view.findViewById(R.id.iv_sender);
        tv_sender_id = view.findViewById(R.id.tv_sender_id);
        tv_gift_info = view.findViewById(R.id.tv_gift_info);

        iv_gift_icon = view.findViewById(R.id.iv_gift_icon);
        iv_gift_icon.setVisibility(View.INVISIBLE);

        tv_gift_num = view.findViewById(R.id.tv_gift_num);
        tv_gift_num.setVisibility(View.INVISIBLE);
        //导入动画
        gift_left_in = AnimationUtils.loadAnimation(getContext(),R.anim.gift_left_in);
        gift_icon_left_in = AnimationUtils.loadAnimation(getContext(), R.anim.gift_icon_left_in);
        gift_num_scale = AnimationUtils.loadAnimation(getContext(), R.anim.gift_num_scale);

        gift_left_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                iv_gift_icon.setVisibility(View.INVISIBLE);
                tv_gift_num.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_gift_icon.setAnimation(gift_left_in);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gift_icon_left_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                iv_gift_icon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv_gift_num.setAnimation(gift_num_scale);
                tv_gift_num.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
    public void bindData(GiftMsgInfo info){
        if(info!=null){
            if(TextUtils.isEmpty(info.getAvatar())){
                ImageUtils.getInstance().loadCircle(R.mipmap.splash_anima,iv_sender);
            }else {
                ImageUtils.getInstance().loadCircle(info.getAvatar(),iv_sender);
            }
            if(!TextUtils.isEmpty(info.getAdouID())){
                tv_sender_id.setText(info.getAdouID());
            }
            if(info.getGift()!=null){
                tv_gift_info.setText("送出了"+info.getGift().getName());
                iv_gift_icon.setBackgroundResource(info.getGift().getResId());
            }
            if(info.getGiftNum()>=1){
                tv_gift_num.setText("X"+info.getGiftNum());

            }
        }

    }
    //设置数量
    public void setGiftNum(int num){
        if(num>1){
            tv_gift_num.setText("X"+num);
        }

    }
    //让礼物执行 动画
    public void startAnimate(){
        rl_gift_mormal.setAnimation(gift_left_in);
    }
}
