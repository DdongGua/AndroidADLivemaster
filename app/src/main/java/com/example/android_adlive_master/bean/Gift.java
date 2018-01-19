package com.example.android_adlive_master.bean;

import com.example.android_adlive_master.R;

/**
 * Created by 亮亮 on 2018/1/18.
 */

public class Gift {
    int resId;
    String name;
    int price;
    GiftType type;
    boolean isSelected;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static final Gift gift0 = new Gift(R.mipmap.xuegao, "雪糕", 1, GiftType.Repeat, false);
    public static final Gift gift1 = new Gift(R.mipmap.qishui, "汽水", 5, GiftType.Repeat, false);
    public static final Gift gift2 = new Gift(R.mipmap.juanzhi, "卷纸", 10, GiftType.Repeat, false);
    public static final Gift gift3 = new Gift(R.mipmap.zhiwu, "多肉植物", 15, GiftType.Repeat, false);
    public static final Gift gift4 = new Gift(R.mipmap.heifengli, "凤梨", 20, GiftType.Repeat, false);
    public static final Gift gift5 = new Gift(R.mipmap.yusan, "雨伞", 30, GiftType.Repeat, false);
    public static final Gift gift6 = new Gift(R.mipmap.shoubiao, "手表", 100, GiftType.Repeat, false);
    public static final Gift gift7 = new Gift(R.mipmap.youxiji, "游戏机", 150, GiftType.Repeat, false);
    public static final Gift gift8 = new Gift(R.mipmap.xiangji, "相机", 200, GiftType.Repeat, false);
    public static final Gift gift9 = new Gift(R.mipmap.chengbao, "城堡", 500, GiftType.Repeat, false);
    public static final Gift gift10 = new Gift(R.mipmap.huojian, "火箭", 1000, GiftType.Repeat, false);
    public static final Gift gift11 = new Gift(R.mipmap.xuegao, "雪糕", 8888, GiftType.FullScreen, false);
    public static final Gift giftEmpty = new Gift(0, "空", 0, GiftType.Empty, false);


    public Gift(int resId, String name, int price, GiftType type, boolean isSelected) {
        this.resId = resId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.isSelected = isSelected;
    }

    public Gift() {
    }

    enum GiftType {
        Repeat, FullScreen, Empty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GiftType getType() {
        return type;
    }

    public void setType(GiftType type) {
        this.type = type;
    }
}
