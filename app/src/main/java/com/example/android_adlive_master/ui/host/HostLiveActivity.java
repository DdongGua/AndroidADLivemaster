package com.example.android_adlive_master.ui.host;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.android_adlive_master.R;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.livesdk.ILVLiveManager;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public class HostLiveActivity extends Activity implements HostLiveContract.View {
    private AVRootView avRootView;
    private ImageView iv_switch_camera,iv_close;

    private HostLivePresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_live);
        initView();
        initPresenter();
        initListener();
        initCreateHost();
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                //退出直播然后关闭
                finish();
            }
        });
    }

    private void initListener() {
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                //退出直播，然后关闭
                finish();
            }
        });


    }

    private void initCreateHost() {
        presenter.createHost();
    }

    private void initPresenter() {
       this.presenter=new HostLivePresenter(this);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        avRootView = findViewById(R.id.arv_root);
        iv_switch_camera = findViewById(R.id.iv_switch_camera);
        iv_close=findViewById(R.id.iv_close);
        //将avrootview添加
        ILVLiveManager.getInstance().setAvVideoView(avRootView);
    }
    @Override
    protected void onPause() {
        super.onPause();
        ILVLiveManager.getInstance().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ILVLiveManager.getInstance().onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出直播
        ILVLiveManager.getInstance().onDestory();
    }
}
