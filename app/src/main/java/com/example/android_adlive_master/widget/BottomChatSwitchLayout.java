package com.example.android_adlive_master.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android_adlive_master.R;

/**
 * Created by 亮亮 on 2018/1/15.
 */

public class BottomChatSwitchLayout extends FrameLayout {

    private LayoutInflater layoutInflater;
    private CheckBox checkBox;
    private EditText et_content;
    private TextView tv_send;
    OnMsgSendListener mListener;

    public BottomChatSwitchLayout(@NonNull Context context) {
        super(context);
        layoutInflater = LayoutInflater.from(context);
        init();
    }

    public BottomChatSwitchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = LayoutInflater.from(context);
        init();
    }
    public void init(){
        View view = layoutInflater.inflate(R.layout.bottom_chat, this, true);
        checkBox = view.findViewById(R.id.checkbox);
        et_content = view.findViewById(R.id.et_content);
        et_content.setHint("开始您的聊天吧~~");
        tv_send = view.findViewById(R.id.tv_send);
        tv_send.setClickable(true);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    et_content.setHint("开启您的弹幕聊骚吧~~");
                }else {
                    et_content.setHint("开始您的聊天吧~~");
                }
            }
        });
        tv_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_content.getText().toString().trim();
                if(checkBox.isChecked()){
                   if(mListener!=null){
                       mListener.danmu(text);
                   }
                }else {
                    if(mListener!=null){
                        mListener.sendMsg(text);
                    }
                }
            }
        });


    }
    public interface OnMsgSendListener{
        void sendMsg(String text);//普通消息
        void danmu(String text);//弹幕
    }
    public void setOnMsgListener(OnMsgSendListener listener){
        mListener=listener;

    }
}
