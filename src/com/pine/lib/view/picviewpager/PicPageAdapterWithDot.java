package com.pine.lib.view.picviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pine.lib.R;
import com.pine.lib.base.activity.A;

import java.util.List;

/**
 * Created by ben on 13/07/2017.
 */

public class PicPageAdapterWithDot extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private ViewPager.OnPageChangeListener listener;
    private List<String> picUrls;

    // 返回按钮控件
    private PicViewPager viewPager;
    // 标题Tv
    private LinearLayout container;

    public PicPageAdapterWithDot(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.pic_viewpager, this);

        // 获取控件
        viewPager = (PicViewPager) findViewById(R.id.pic_viewpager_inlab);
        container = (LinearLayout) findViewById(R.id.ll_container);

        viewPager.addOnPageChangeListener(this);
    }


    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.listener = listener;


    }

    public void setPics(List<String> picUrls) {
        this.picUrls = picUrls;
        viewPager.setPics(picUrls);
        loadDots(picUrls.size(), 0);
    }

    private void loadDots(int size, int currentIndex) {
        container.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView dot = new ImageView(A.a());
            if (i == currentIndex) {
                dot.setImageResource(R.drawable.dot_red);//设置当前页的圆点
            } else {
                dot.setImageResource(R.drawable.dot_brown);//其余页的圆点
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 10;//设置圆点边距
            }
            dot.setLayoutParams(params);
            container.addView(dot);//将圆点添加到容器中
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (listener != null)
            listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null)
            listener.onPageSelected(position);
        loadDots(picUrls.size(), position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (listener != null)
            listener.onPageScrollStateChanged(state);
    }

}
