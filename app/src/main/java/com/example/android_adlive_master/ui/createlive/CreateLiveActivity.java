package com.example.android_adlive_master.ui.createlive;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.app.QiniuConfig;
import com.example.android_adlive_master.engine.PicChooseHelper;
import com.example.android_adlive_master.qiniu.QiniuUploadHelper;
import com.example.android_adlive_master.utils.ImageUtils;
import com.example.android_adlive_master.utils.ToastUtils;
import com.example.android_adlive_master.widget.dialog.EditProfileAvatarDialog;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by 亮亮 on 2018/1/9.
 */

public class CreateLiveActivity extends Activity implements CreateLiveContract.View, View.OnClickListener {

    private EditText et_room_name;
    private Button bt_createlive;
    private ImageView iv_cover;
    private FrameLayout fl;
    private CreateLiveContract.Presenter presenter;
    String coverUrl, liveName;
    private EditProfileAvatarDialog selectpicdialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlive);
        initView();
        initPresenter();
        initListener();
    }

    private void initListener() {
        fl.setOnClickListener(this);
        bt_createlive.setOnClickListener(this);
    }

    private void initPresenter() {
        presenter = new CreateLivePresenter(this);
    }

    private void initView() {
        iv_cover = findViewById(R.id.iv_cover);
        fl = findViewById(R.id.fl);
        et_room_name = findViewById(R.id.et_room_name);
        bt_createlive = findViewById(R.id.bt_createlive);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl:
                //设置封面
                showSelectPicDialog();
                break;
            case R.id.bt_createlive:
                //创建直播
                liveName = et_room_name.getText().toString().trim();
                presenter.createLive(coverUrl, liveName);
                break;
        }

    }


    @Override
    public void onCreateSuccess() {

    }

    @Override
    public void onCreateFailed() {
        ToastUtils.show("信息不能为空，创建失败~");

    }

    private void showSelectPicDialog() {
        selectpicdialog = new EditProfileAvatarDialog(this, R.style.dialog_ios_style);
        selectpicdialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PicChooseHelper.getInstance(this).onActivityResult(requestCode, resultCode, data, PicChooseHelper.CropType.Cover, new PicChooseHelper.OnPicReadyListener() {
            @Override
            public void onReady(Uri outUri) {
                //当图片截取完之后怎么办
                iv_cover.setVisibility(View.VISIBLE);
                ImageUtils.getInstance().load(outUri, iv_cover);
                selectpicdialog.dismiss();
                //先把图片传给七牛
                String path = outUri.getPath();
                File file = new File(path);
                if (!file.exists()) {
                    ToastUtils.show("获取本地图片失败！");
                } else {
                    try {
                        QiniuUploadHelper.uploadPic(file.getAbsolutePath(), file.getName(), new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject response) {
                                coverUrl= QiniuConfig.QINIU_HOST+key;
                                ToastUtils.show("封面设置成功");
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }
}
