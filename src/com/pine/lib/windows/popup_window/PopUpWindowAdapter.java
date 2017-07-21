package com.pine.lib.windows.popup_window;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pine.lib.R;
import com.pine.lib.base.activity.A;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 14/07/2017.
 */

public class PopUpWindowAdapter extends BaseAdapter {

    private List<PopupBean> data = new ArrayList<>();

    public PopUpWindowAdapter(List<PopupBean> data) {
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = View.inflate(A.a(),
                    R.layout.popup_window_adapter, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.title);
        ImageView img = (ImageView) convertView.findViewById(R.id.checkbox);


        textView.setText(data.get(i).value);
        if (data.get(i).checked) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
