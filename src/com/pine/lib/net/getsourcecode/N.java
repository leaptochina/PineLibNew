package com.pine.lib.net.getsourcecode;

import com.baidu.mobstat.g;
import com.pine.lib.func.debug.G;

import afinal.FinalHttp;
import afinal.http.AjaxCallBack;
import afinal.http.AjaxParams;

/**
 * Created by ben on 12/07/2017.
 */

public class N extends AjaxCallBack<Object> {
    private static G g = new G(N.class);
    private static N n = null;

    private FinalHttp finalHttp;

    private N()
    {
        finalHttp = new FinalHttp();
    }

    public void get(String url, OnNetFinish onNetFinish, String tag)
    {
        g.d("get:" + tag + url);
        finalHttp.get(url, new MyNetworkCallback(url, onNetFinish, tag));
    }

    public void get(String url, OnNetFinish onNetFinish)
    {
        get(url, onNetFinish, "");
    }

    public void post(String url, AjaxParams ajaxParams, OnNetFinish onNetFinish, String tag)
    {
        g.d("post:" + tag + ", " + url);
        finalHttp.post(url, ajaxParams, new MyNetworkCallback(url, onNetFinish, tag));
    }

    public void post(String url, AjaxParams ajaxParams, OnNetFinish onNetFinish)
    {
        post( url,  ajaxParams,  onNetFinish,  "");
    }


    public static N i() {
        if (n == null) {
            n = new N();
        }
        return n;
    }
}
