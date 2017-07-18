package com.pine.lib.net.getsourcecode;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import afinal.http.AjaxCallBack;

/**
 * Created by ben on 12/07/2017.
 */

public class MyNetworkCallback extends AjaxCallBack<Object> {

    private  OnNetFinish onNetFinish;
    private  String url ;
    private String tag;

    public MyNetworkCallback(String url, OnNetFinish onNetFinish, String tag)
    {
        this.url = url;
        this.onNetFinish = onNetFinish;
        this.tag = tag;
    }

    public MyNetworkCallback() {
        super();
    }

    @Override
    public boolean isProgress() {
        return super.isProgress();
    }

    @Override
    public int getRate() {
        return super.getRate();
    }

    @Override
    public AjaxCallBack<Object> progress(boolean progress, int rate) {
        return super.progress(progress, rate);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onLoading(long count, long current) {
        super.onLoading(count, current);
    }

    @Override
    public void onSuccess(Object o) {
        onNetFinish.onFinish(true, tag, o.toString());
    }

    @Override
    public void onFailure(Throwable t, String strMsg) {
        onNetFinish.onFinish(false, tag, strMsg);
    }
}
