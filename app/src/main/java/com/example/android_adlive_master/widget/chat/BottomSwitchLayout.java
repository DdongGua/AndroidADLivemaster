package com.example.android_adlive_master.widget.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.android_adlive_master.R;

/**
 * Created by 亮亮 on 2018/1/15.
 */

public class BottomSwitchLayout extends FrameLayout implements View.OnClickListener {

    private LayoutInflater layoutInflater;
    private ImageView iv_switch_chat;
    private ImageView iv_switch_close;
    public ImageView iv_switch_gift;
    OnSwitchListener mListener;

    public BottomSwitchLayout(@NonNull Context context) {
        super(context);
        layoutInflater = LayoutInflater.from(context);
        init();

    }

    public BottomSwitchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        View view = layoutInflater.inflate(R.layout.bottom_chat_or_close, this, true);
        iv_switch_chat = view.findViewById(R.id.iv_switch_chat);
        iv_switch_close = view.findViewById(R.id.iv_switch_close);
        iv_switch_gift = view.findViewById(R.id.iv_switch_gift);
        iv_switch_chat.setOnClickListener(this);
        iv_switch_close.setOnClickListener(this);
        iv_switch_gift.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_switch_chat:
                //切换到聊天布局
                if (mListener != null) {
                    mListener.onChat();
                }
                break;
            case R.id.iv_switch_close:
                //退出直播
                if (mListener != null) {
                    mListener.onClose();
                }
                break;
            case R.id.iv_switch_gift:
                //调起发送礼物的dialog
                if (mListener != null) {
                    mListener.onGift();
                }

                break;
            default:
                break;
        }

    }

    public interface OnSwitchListener {
        void onChat();

        void onClose();

        //发送礼物
        void onGift();
    }

    public void setOnSwitchListener(OnSwitchListener listener) {
        mListener = listener;

    }
}
