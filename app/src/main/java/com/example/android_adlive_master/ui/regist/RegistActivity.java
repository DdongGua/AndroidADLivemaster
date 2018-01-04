package com.example.android_adlive_master.ui.regist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.utils.ToastUtils;

/**
 * Created by 亮亮 on 2018/1/1.
 */

public class RegistActivity extends Activity implements View.OnClickListener, RegistContract.View {

    private Toolbar toolbar;
    private EditText et_regist_account;
    private EditText et_regist_pass;
    private EditText et_regist_confirm;
    private Button bt_regist;
    private TextView tv_regist_info;
    private RegistContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        //初始化数据
        initView();
        //初始化toolbar
        initToolbar();
        //初始化点击事件
        initListener();
        //初始化接口
        initPresenter();
    }

    private void initPresenter() {
        presenter=new RegistPresenter(this);
    }

    private void initListener() {
        bt_regist.setOnClickListener(this);
        tv_regist_info.setOnClickListener(this);
    }

    private void initToolbar() {
        toolbar.setTitle("注册");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        et_regist_account = findViewById(R.id.et_regist_account);
        et_regist_pass = findViewById(R.id.et_regist_pass);
        et_regist_confirm = findViewById(R.id.et_regist_confirm);
        bt_regist = findViewById(R.id.bt_regist);
        tv_regist_info = findViewById(R.id.tv_regist_info);

    }

    @Override
    public void registSuccess() {
        ToastUtils.show("注册成功");
        finish();

    }

    @Override
    public void registError() {

    }

    @Override
    public void registInfoEmpty() {
        ToastUtils.show("账号或密码为空~");

    }

    @Override
    public void registInfoError() {
        ToastUtils.show("账号或密码不能少于8位~");

    }

    @Override
    public void registConfirmPassError() {
        ToastUtils.show("两次输入的密码不一样，请检查！");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_regist:
                String account = et_regist_account.getText().toString().trim();
                String pass = et_regist_pass.getText().toString().trim();
                String confirmPass= et_regist_confirm.getText().toString().trim();
                presenter.regist(account,pass,confirmPass);

                break;
            case R.id.tv_regist_info:
                break;
            default:
                break;
        }

    }
}
