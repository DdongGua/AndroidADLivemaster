package com.example.android_adlive_master.widget.danmu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.bean.DanmuMsgInfo;
import com.example.android_adlive_master.utils.ImageUtils;

/**
 * Created by 亮亮 on 2018/1/17.
 */

public class DanmuItemView extends FrameLayout {

    private LayoutInflater inflater;
    private ImageView iv_danmu_avatar;
    private TextView tv_danmu_adouid;
    private TextView tv_danmu_msg;

    public DanmuItemView(@NonNull Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init();
    }

    public DanmuItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
    }
    public void init(){
        View view = inflater.inflate(R.layout.danmu_item, this, true);
        iv_danmu_avatar = view.findViewById(R.id.iv_danmu_avatar);
        tv_danmu_adouid = view.findViewById(R.id.tv_danmu_adouid);
        tv_danmu_msg = view.findViewById(R.id.tv_danmu_msg);
        setDefault();
    }

    //设置默认图片
    private void setDefault() {
        ImageUtils.getInstance().loadCircle(R.mipmap.female,iv_danmu_avatar);
    }
    //提供设置内容的方法
    public void bindData(DanmuMsgInfo info){
        if(TextUtils.isEmpty(info.getAvatar())){
            //设置默认头像
            ImageUtils.getInstance().loadCircle(R.mipmap.female,iv_danmu_avatar);
        }else {
            ImageUtils.getInstance().loadCircle(info.getAvatar(),iv_danmu_avatar);
        }
        if(!TextUtils.isEmpty(info.getAdouID())){
            tv_danmu_adouid.setText(info.getAdouID());
        }
        if(!TextUtils.isEmpty(info.getText())){
            tv_danmu_msg.setText(info.getText());
        }

    }
}
