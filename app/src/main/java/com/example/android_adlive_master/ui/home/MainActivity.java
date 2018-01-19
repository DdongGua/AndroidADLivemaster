package com.example.android_adlive_master.ui.home;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.ui.createlive.CreateLiveActivity;
import com.example.android_adlive_master.ui.mine.MineFragment;

/**
 * @author 亮亮
 */
public class MainActivity extends FragmentActivity {

    private FragmentManager fragmentManager;
    private Toolbar toolbar;
//    private FrameLayout tabcontext;
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
        initToolbar("首页");
        updateview();
    }

    private void updateview() {
        Intent intent=getIntent();
        //说明从别的activity跳转过来
        if(intent!=null){
            String from = intent.getStringExtra("from");
            //说明是从编辑页面跳转过来的
            if("EditProfileActivity".equals(from)){
                tabHost.setCurrentTab(2);
            }
        }
    }


    public void initToolbar(String str) {
        toolbar.setTitle(str);

    }
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup(this,fragmentManager,R.id.tabcontext);
        TabHost.TabSpec tabSpec_home = tabHost.newTabSpec("home").setIndicator(getIndicatorView("home"));
        TabHost.TabSpec tabSpec_create = tabHost.newTabSpec("create").setIndicator(getIndicatorView("create"));
        TabHost.TabSpec tabSpec_mine = tabHost.newTabSpec("mine").setIndicator(getIndicatorView("mine"));
        tabHost.addTab(tabSpec_home,HomeFragment.class,null);
        //发起直播按钮
        tabHost.addTab(tabSpec_create,null,null);
        tabHost.addTab(tabSpec_mine,MineFragment.class,null);
        tabHost.getTabWidget().setDividerDrawable(R.color.transprant);
        //定义事件将tabspec事件拦截
        tabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,CreateLiveActivity.class);
                startActivity(intent);
            }
        });
    }

    public View getIndicatorView(String tag) {
        View v= LayoutInflater.from(this).inflate(R.layout.tabspec,null,false);
         ImageView mImg=v.findViewById(R.id.mImg);
         if("home".equals(tag)){
             mImg.setBackgroundResource(R.drawable.selector_tab_home);
         }else if("mine".equals(tag)){
             mImg.setImageResource(R.drawable.selector_tab_mine);
         }else if("create".equals(tag)){
             mImg.setImageResource(R.mipmap.live2);
         }
        return v;
    }
}
