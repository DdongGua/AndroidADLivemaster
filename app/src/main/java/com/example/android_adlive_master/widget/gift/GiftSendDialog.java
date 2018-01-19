package com.example.android_adlive_master.widget.gift;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.bean.Gift;

import java.util.ArrayList;

/**
 * Created by 亮亮 on 2018/1/18.
 */

public class GiftSendDialog implements View.OnClickListener {
    //gridview集合
    ArrayList<GiftGridView> gridViewLists = new ArrayList<>();
    ArrayList<Gift> allGifts = new ArrayList<>();
    Activity activity;
    private WindowManager wm;
    private Display display;
    private LayoutInflater inflater;
    private ViewPager vp_gift_send;
    private ImageView iv_indicator0;
    private ImageView iv_indicator1;
    private Button bt_send_gift;
    private View view;
    private final Dialog dialog;


    public GiftSendDialog(Activity activity) {
        this.activity = activity;
        dialog = new Dialog(activity);
        init();
        initListener();
    }

    private void initListener() {
        vp_gift_send.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    iv_indicator0.setBackgroundResource(R.mipmap.indicator_selected);
                    iv_indicator1.setBackgroundResource(R.mipmap.indicator_normal);
                } else if (1 == position) {
                    iv_indicator0.setBackgroundResource(R.mipmap.indicator_normal);
                    iv_indicator1.setBackgroundResource(R.mipmap.indicator_selected);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void show() {
        dialog.show();
    }

    public void init() {
        initAllGift();
        wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        inflater = LayoutInflater.from(activity);
        view = inflater.inflate(R.layout.dialog_gift_send, null, false);
        vp_gift_send = view.findViewById(R.id.vp_gift_send);
        iv_indicator0 = view.findViewById(R.id.iv_indicator0);
        iv_indicator1 = view.findViewById(R.id.iv_indicator1);
        bt_send_gift = view.findViewById(R.id.bt_send_gift);
        //准备两个Gridview
        initGridView();
        dialog.setContentView(view);
        //通过window设置dialog的宽高和位置
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = display.getWidth();
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);

    }

    private void initGridView() {
        //准备viewPager中的两个GrideView
        ArrayList<Gift> gift0 = new ArrayList<>();
        ArrayList<Gift> gift1 = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 8;
        //将大集分成两个集合
        gift0.addAll(allGifts.subList(startIndex, endIndex));
        gift1.addAll(allGifts.subList(endIndex, allGifts.size()));

        GiftGridView giftGridView0 = new GiftGridView(activity);
        giftGridView0.setGiftData(gift0);

        GiftGridView giftGridView1 = new GiftGridView(activity);
        giftGridView1.setGiftData(gift1);
        //将数据添加进去git
        gridViewLists.add(giftGridView0);
        gridViewLists.add(giftGridView1);
        //设置viewPager适配器
        GiftViewPageAdapter giftViewPageAdapter = new GiftViewPageAdapter();
        vp_gift_send.setAdapter(giftViewPageAdapter);

    }

    private void initAllGift() {
        allGifts.add(Gift.gift0);
        allGifts.add(Gift.gift1);
        allGifts.add(Gift.gift2);
        allGifts.add(Gift.gift3);
        allGifts.add(Gift.gift4);
        allGifts.add(Gift.gift5);
        allGifts.add(Gift.gift6);
        allGifts.add(Gift.gift7);
        allGifts.add(Gift.gift8);
        allGifts.add(Gift.gift9);
        allGifts.add(Gift.gift10);
        allGifts.add(Gift.gift11);
        allGifts.add(Gift.giftEmpty);
        allGifts.add(Gift.giftEmpty);
        allGifts.add(Gift.giftEmpty);
        allGifts.add(Gift.giftEmpty);


    }

    public GiftSendDialog(Activity activity, int themeResId) {
        this.activity = activity;
        //把dialog实例化
        dialog = new Dialog(activity, themeResId);
        init();
        initListener();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_camera:
                break;
            case R.id.tv_photo:
                break;
            case R.id.iv_cancel:
                break;
            default:
                break;
        }

    }

    class GiftViewPageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return gridViewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            GiftGridView giftGridView = gridViewLists.get(position);
            if (giftGridView.getParent() == null) {
                container.addView(giftGridView);
            }
            return giftGridView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView(gridViewLists.get(position));
        }
    }

}
