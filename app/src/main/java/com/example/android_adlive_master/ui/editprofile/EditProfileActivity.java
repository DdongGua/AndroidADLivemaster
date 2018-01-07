package com.example.android_adlive_master.ui.editprofile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.app.QiniuConfig;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.engine.PicChooseHelper;
import com.example.android_adlive_master.qiniu.QiniuUploadHelper;
import com.example.android_adlive_master.utils.ToastUtils;
import com.example.android_adlive_master.ui.home.MainActivity;
import com.example.android_adlive_master.widget.EditProfileAvatarDialog;
import com.example.android_adlive_master.widget.EditProfileDialog;
import com.example.android_adlive_master.widget.EditProfileDialog2;
import com.example.android_adlive_master.widget.EditProfileItem;
import com.example.android_adlive_master.widget.EditProfile_Gender_Dialog;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.tencent.TIMCallBack;
import com.tencent.TIMFriendGenderType;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;

import org.json.JSONObject;

import java.io.File;

public class EditProfileActivity extends Activity implements EditProfileContract.View, View.OnClickListener {

    private static final String TAG = "EditProfileActivity";

    private EditProfileContract.Presenter presenter;
    private Toolbar toolbar;

    private EditProfileItem ep_avatar;
    private EditProfileItem ep_nickname;
    private EditProfileItem ep_adouid;
    private EditProfileItem ep_gender;

    private EditProfileItem ep_xingzuo;
    private EditProfileItem ep_active_area;
    private EditProfileItem ep_signature;
    private EditProfileItem ep_home_country;
    private EditProfileItem ep_job;
    EditProfileDialog editProfileDialog;
    EditProfileDialog2 activeDialog;
    EditProfileDialog2 signatrueDialog;
    EditProfile_Gender_Dialog genderDialog;
    EditProfileAvatarDialog avatarDialog;
    private Uri outUri;
    private Button bt_save_profile;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sp = getSharedPreferences("isfirstenter", MODE_PRIVATE);
        edit = sp.edit();
        edit.putBoolean("isfirst",false);
        edit.commit();
        initView();
        initPresenter();
        initListener();
    }

    private void initListener() {
        //添加导航按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ep_avatar.setOnClickListener(this);
        ep_nickname.setOnClickListener(this);
        ep_adouid.setOnClickListener(this);
        ep_gender.setOnClickListener(this);
        ep_xingzuo.setOnClickListener(this);
        ep_active_area.setOnClickListener(this);
        ep_signature.setOnClickListener(this);
        ep_home_country.setOnClickListener(this);
        ep_job.setOnClickListener(this);
        bt_save_profile.setOnClickListener(this);


    }

    private void initPresenter() {
        this.presenter = new EditProfilePresenter(this);
        presenter.getUserInfo();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        ep_avatar = findViewById(R.id.ep_avatar);
        ep_nickname = findViewById(R.id.ep_nickname);
        ep_adouid = findViewById(R.id.ep_adouid);
        ep_gender = findViewById(R.id.ep_gender);
        ep_xingzuo = findViewById(R.id.ep_xingzuo);
        ep_active_area = findViewById(R.id.ep_active_area);
        ep_signature = findViewById(R.id.ep_signature);
        ep_home_country = findViewById(R.id.ep_home_country);
        ep_job = findViewById(R.id.ep_job);
        bt_save_profile = findViewById(R.id.bt_save_profile);

    }

    @Override
    public void updateView(AdouTimUserProfile profile) {
        if (profile != null) {
            TIMUserProfile userProfile = profile.getProfile();
            String nickName = userProfile.getNickName();
            String faceUrl = userProfile.getFaceUrl();
            String identifier = userProfile.getIdentifier();
            TIMFriendGenderType gender = userProfile.getGender();
            String location = userProfile.getLocation();
            String selfSignature = userProfile.getSelfSignature();
            if (!TextUtils.isEmpty(faceUrl)) {
                ep_avatar.setAvatar(faceUrl);
            }
            if (!TextUtils.isEmpty(nickName)) {
                ep_nickname.setValue(nickName);
            }
            if (!TextUtils.isEmpty(identifier)) {
                ep_adouid.setValue(identifier);
            }
            if (!TextUtils.isEmpty(location)) {
                ep_active_area.setValue(location);
            }
            if (!TextUtils.isEmpty(selfSignature)) {
                ep_signature.setValue(selfSignature);
            }
            if (gender == TIMFriendGenderType.Male) {
                ep_gender.setValue("男");
            } else if (gender == TIMFriendGenderType.Female) {
                ep_gender.setValue("女");
            } else {
                ep_gender.setValue("不详");
            }


        }

    }

    @Override
    public void onGetInfoFailed() {

    }

    @Override
    public void updateInfoError() {
        ToastUtils.show("更新信息失败");

    }

    @Override
    public void updateInfoSuccess() {
        ToastUtils.show("更新信息成功");
        presenter.getUserInfo();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ep_avatar:
                showSelectAvatarDialog();
                break;
            case R.id.ep_nickname:
                showEditNickNameDialog();
                break;
            case R.id.ep_gender:
                showEditGenderDialog();
                break;
            case R.id.ep_active_area:
                showEditActiveDialog();
                break;
            case R.id.ep_job:
                break;
            case R.id.ep_signature:
                showEditSignatureDialog();

                break;
            case R.id.ep_xingzuo:
                break;
            case R.id.bt_save_profile:
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }


    /*
    *点击修改头像
    */
    public void showSelectAvatarDialog() {
        avatarDialog = new EditProfileAvatarDialog(this, R.style.common_dialog_style);
        avatarDialog.show();
    }

    /*
     *点击修改昵称
     */
    public void showEditNickNameDialog() {
        editProfileDialog = new EditProfileDialog(this, new EditProfileDialog.OnEditChangedListener() {

            @Override
            public void onChanged(String value) {
                //更改服务器上内容
                TIMFriendshipManager.getInstance().setNickName(value, new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        //更新信息失败
                        updateInfoError();
                        Log.e(TAG, "onError: " + s);
                    }

                    @Override
                    public void onSuccess() {
                        editProfileDialog.dismiss();
                        editProfileDialog = null;
                        //更新信息成功
                        presenter.onUpdateInfoSuccess();


                    }
                });
            }

            @Override
            public void onContentEmpty() {

            }
        });
        editProfileDialog.setTitleAndIcon("请输入您的昵称", R.mipmap.splash_image);
        editProfileDialog.show();

    }

    /*
     *点击选择性别
     */
    private void showEditGenderDialog() {
        genderDialog = new EditProfile_Gender_Dialog(this, new EditProfile_Gender_Dialog.OnProfileChangedListener() {
            @Override
            public void onChangeSuccess(String value) {
                TIMFriendGenderType type;
                if ("男生".equals(value)) {
                    type = TIMFriendGenderType.Male;
                } else if ("女生".equals(value)) {
                    type = TIMFriendGenderType.Female;
                } else {
                    type = TIMFriendGenderType.Unknow;
                }
                TIMFriendshipManager.getInstance().setGender(type, new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onSuccess() {
                        genderDialog.hide();
                        genderDialog = null;
                        presenter.onUpdateInfoSuccess();
                    }
                });
            }

            @Override
            public void onChangeError() {

            }
        });
        genderDialog.setTitleAndIcon("请选择您的性别：", R.mipmap.female);
        genderDialog.show();
    }

    /*
     *点击更改活跃地带
     */
    private void showEditActiveDialog() {
        activeDialog = new EditProfileDialog2(this, new EditProfileDialog2.OnProfileChangedListener() {
            @Override
            public void onChangeSuccess(final String value) {
                //先通过api 设置location
                String value2 = value;
                TIMFriendshipManager.getInstance().setLocation(value2, new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        updateInfoError();
                    }

                    @Override
                    public void onSuccess() {
                        //再展示新的信息
//                         ep_active_area.setValue(value2);
                        activeDialog.hide();
                        activeDialog = null;
                        presenter.onUpdateInfoSuccess();

                    }
                });
                //更新应用缓存
            }

            @Override
            public void onChangeError() {
                activeDialog.hide();
                activeDialog = null;
                updateInfoError();

            }
        });
        activeDialog.setTitleAndIcon("请输入您的活跃地带：", R.mipmap.female);
        activeDialog.show();

    }

    /*
    *点击更改个性签名
    */
    private void showEditSignatureDialog() {

        signatrueDialog = new EditProfileDialog2(this, new EditProfileDialog2.OnProfileChangedListener() {
            @Override
            public void onChangeSuccess(String value) {
                TIMFriendshipManager.getInstance().setSelfSignature(value, new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                    }

                    @Override
                    public void onSuccess() {
                        signatrueDialog.hide();
                        signatrueDialog = null;
                        presenter.onUpdateInfoSuccess();
                    }
                });
            }

            @Override
            public void onChangeError() {
            }
        });
        signatrueDialog.setTitleAndIcon("请输入您的个性签名", R.mipmap.male);
        signatrueDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PicChooseHelper.getInstance(this).onActivityResult(requestCode, resultCode, data, new PicChooseHelper.OnAvatarReadyListener() {
            @Override
            public void onReady(Uri outUri) {
                ep_avatar.setAvatar(outUri);
                avatarDialog.dismiss();
                //需要把路径传到服务器（七牛云）
                String path = outUri.getPath();
                File file = new File(path);
                String absolutePath = file.getAbsolutePath();
                String name = file.getName();
                try {
                    QiniuUploadHelper.uploadPic(absolutePath, name, new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject response) {
                            //res包含hash、key等信息，具体字段取决于上传策略的设置

                            if (info.isOK()) {
                                updateNetAvatarInfo(QiniuConfig.QINIU_HOST + key);

                            } else {
                                //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因


                                Logger.i("qiniu", "Upload Fail");

                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void updateNetAvatarInfo(String url) {
        //需要把图片同步到服务器

        TIMFriendshipManager.getInstance().setFaceUrl(url, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                updateInfoError();
            }

            @Override
            public void onSuccess() {
                //重置信息
                presenter.onUpdateInfoSuccess();

            }
        });

    }


}

