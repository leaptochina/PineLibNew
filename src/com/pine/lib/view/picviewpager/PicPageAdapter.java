package com.pine.lib.view.picviewpager;

import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.pine.lib.base.activity.A;
import com.pine.lib.net.pic.PicDataBean;
import com.pine.lib.net.pic.SetPicForLib;

import java.util.ArrayList;
import java.util.List;

import afinal.FinalBitmap;

/**
 * Created by ben on 13/07/2017.
 */

public class PicPageAdapter extends PagerAdapter {

    public List<String> picUrls = new ArrayList<String>();
    public List<ImageView> imageViews = new ArrayList<ImageView>();

    @Override
    public int getCount() {
        for (int i = imageViews.size(); i < picUrls.size(); i++)
        {
            ImageView iv = new ImageView(A.a());
            SetPicForLib.i().setPic(new PicDataBean(iv, picUrls.get(i)));
            imageViews.add(iv);
        }
        return picUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = imageViews.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
