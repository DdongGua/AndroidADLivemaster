package com.example.android_adlive_master.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.ui.profile.ProfileActivity;
import com.example.android_adlive_master.ui.regist.RegistActivity;
import com.example.android_adlive_master.utils.ToastUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
    private EditText et_login_username;
    private EditText et_login_password;
    private Button bt_login;
    private TextView tv_login_info;
    private LoginContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化数据
        initView();
        //初始化接口
        initPresenter();
        //初始化点击事件
        initListener();
    }

    private void initListener() {
        bt_login.setOnClickListener(this);
        tv_login_info.setOnClickListener(this);
    }

    private void initPresenter() {
        this.presenter = new LoginPresenter(this);
    }

    private void initView() {
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        bt_login = findViewById(R.id.bt_login);
        tv_login_info = findViewById(R.id.tv_login_info);
    }


    @Override
    public void loginSuccess() {
        //登录成功
        ToastUtils.show("登录成功");

    }

    @Override
    public void loginFailed(int errCode, String errMsg) {
        //登陆失败
        ToastUtils.show("登陆失败");


    }

    @Override
    public void loginInfoEmpty() {
        //账号或密码为空
        ToastUtils.show("账号或密码为空~");

    }

    @Override
    public void loginInfoError() {
        //账号或密码不够8位
        ToastUtils.show("账号或密码不够8位，请检查！");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String account=et_login_username.getText().toString().trim();
                String pass = et_login_password.getText().toString().trim();
                presenter.login(account,pass);
                break;
            case R.id.tv_login_info:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
