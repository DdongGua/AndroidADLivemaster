package com.example.android_adlive_master.widget.gift;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_adlive_master.R;
import com.example.android_adlive_master.bean.Gift;

import java.util.ArrayList;

/**
 * Created by 亮亮 on 2018/1/18.
 */

public class GiftGridView extends GridView {
    ArrayList<Gift> giftLists = new ArrayList<>();

    private LayoutInflater inflater;
    private GiftGridAdapter giftGridAdapter;

    public GiftGridView(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init();
    }

    public GiftGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        setNumColumns(4);//设置为4列
        giftGridAdapter = new GiftGridAdapter();
        setAdapter(giftGridAdapter);

    }
    //提供方法传入礼物数据
    public void setGiftData(ArrayList<Gift> gifts) {
        //先释放一下资源
        giftLists.clear();
        //再赋值
        giftLists = gifts;
        giftGridAdapter.notifyDataSetChanged();


    }

    //定义礼物的适配器
    class GiftGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return giftLists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_gift, null, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.bindData(giftLists.get(position));

            return convertView;
        }

        class ViewHolder {
            ImageView iv_gift_icon;
            TextView tv_gift_name;
            TextView tv_gift_price;
            ImageView iv_select;

            public ViewHolder(View itemView) {
                iv_gift_icon = itemView.findViewById(R.id.iv_gift_icon);
                tv_gift_name = itemView.findViewById(R.id.tv_gift_name);
                tv_gift_price = itemView.findViewById(R.id.tv_gift_price);
                iv_select = itemView.findViewById(R.id.iv_select);

            }

            public void bindData(Gift gift) {
                if (gift != null) {
                    if (gift.getResId() != 0) {
                        iv_gift_icon.setBackgroundResource(gift.getResId());
                    } else {
                        iv_gift_icon.setBackgroundColor(getContext().getResources().getColor(R.color.transprant));
                    }
                    if (!TextUtils.isEmpty(gift.getName())) {
                        tv_gift_name.setText(gift.getName());
                    }
                    tv_gift_price.setText(gift.getPrice() + "斗币");
                    if (gift.isSelected()) {
                        iv_select.setBackgroundResource(R.mipmap.right);
                    } else {
                        iv_select.setBackgroundColor(getContext().getResources().getColor(R.color.transprant));
                    }
                }

            }
        }
    }
}
