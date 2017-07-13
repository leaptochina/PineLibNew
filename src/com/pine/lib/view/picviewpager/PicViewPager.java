package com.pine.lib.view.picviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 13/07/2017.
 */

public class PicViewPager extends ViewPager {

    private PicPageAdapter picPageAdapter = new PicPageAdapter();



    public PicViewPager(Context context) {
        super(context);
    }

    public PicViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setPics(List<String> picUrls) {
        picPageAdapter.picUrls = picUrls;

        setAdapter(picPageAdapter);

    }


}
