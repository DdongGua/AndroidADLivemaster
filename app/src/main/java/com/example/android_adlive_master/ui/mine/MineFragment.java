package com.example.android_adlive_master.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.bean.AdouTimUserProfile;
import com.example.android_adlive_master.ui.editprofile.EditProfileActivity;
import com.example.android_adlive_master.ui.home.MainActivity;
import com.example.android_adlive_master.ui.profile.ProfileContract;
import com.example.android_adlive_master.ui.profile.ProfilePresenter;
import com.example.android_adlive_master.utils.ImageUtils;
import com.example.android_adlive_master.utils.ToastUtils;
import com.tencent.TIMFriendGenderType;


/**
 * Created by guaju on 2018/1/5.
 */

public class MineFragment extends Fragment  implements ProfileContract.View{
    private static final String TAG = "MineFragment";
    private ConstraintLayout cl_profile;
    private ProfileContract.Presenter presenter;

    private ImageView iv_avatar;
    private TextView tv_nickname;
    private TextView tv_acount_id;
    private ImageView iv_gender;
    private TextView tv_grade;
    private TextView tv_receive;
    private TextView tv_send;
    private MainActivity mainActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initPresenter();
        mainActivity = (MainActivity) getActivity();
        mainActivity.initToolbar("我");
    }

    private void initPresenter() {
        presenter=new ProfilePresenter(this);
    }

    private void initView(View view) {
        cl_profile = view.findViewById(R.id.cl_profile);
        iv_avatar = view.findViewById(R.id.iv_avatar);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        tv_acount_id = view.findViewById(R.id.tv_acount_id);
        iv_gender = view.findViewById(R.id.iv_gender);
        tv_grade = view.findViewById(R.id.tv_grade);
        tv_receive= view.findViewById(R.id.tv_receive);
        tv_send = view.findViewById(R.id.tv_send);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUserProfile();
    }

    private void initListener() {
        cl_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    //更新数据
    @Override
    public void updateProfile(AdouTimUserProfile profile) {
        //更新view,设置图片跟字段
        if (!TextUtils.isEmpty(profile.getProfile().getFaceUrl())){
            ImageUtils.getInstance().loadCircle(profile.getProfile().getFaceUrl(),iv_avatar);
        } else{
            ImageUtils.getInstance().loadCircle(R.mipmap.splash_anima,iv_avatar);
        }


        if (!TextUtils.isEmpty(profile.getProfile().getNickName())){
            tv_nickname.setText(profile.getProfile().getNickName());
        }else {
            tv_nickname.setText("暂无昵称");
        }
        if (!TextUtils.isEmpty(profile.getProfile().getIdentifier())){
            tv_acount_id.setText("阿斗号:"+profile.getProfile().getIdentifier());
        }
        if (profile.getProfile().getGender()== TIMFriendGenderType.Male){
            iv_gender.setBackgroundResource(R.mipmap.male);
        }else if (profile.getProfile().getGender()==TIMFriendGenderType.Female){
            iv_gender.setBackgroundResource(R.mipmap.female);
        }else{
            //默认是男
            iv_gender.setBackgroundResource(R.mipmap.male);
        }
        tv_grade.setText(profile.getGrade()+"");
        tv_send.setText(profile.getFork()+"");
        tv_receive.setText(profile.getFans()+"");

    }

    @Override
    public void updateProfileError() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show("拉取信息失败");
            }
        });
    }
}
