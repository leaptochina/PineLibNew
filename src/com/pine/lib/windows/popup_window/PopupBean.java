package com.pine.lib.windows.popup_window;

import java.io.Serializable;

/**
 * Created by ben on 21/07/2017.
 */

public class PopupBean implements Serializable {
    public String value = "";
    public String key = "";
    public Boolean checked = false;

    public PopupBean(String key, String value, Boolean checked)
    {
        this.key = key;
        this.value = value;
        this.checked = checked;
    }


    public PopupBean(String key, String value)
    {
        this(key, value, false);
    }

    public PopupBean(int key, String value, Boolean checked)
    {
        this(String.valueOf(key), value, checked);
    }

    public PopupBean(int key, String value)
    {
        this(String.valueOf(key), value, false);
    }

    public PopupBean(String value)
    {
        this(value, value, false);
    }
}
