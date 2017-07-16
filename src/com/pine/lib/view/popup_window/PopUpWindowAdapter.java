package com.pine.lib.view.popup_window;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    private List<String> data = new ArrayList<>();

    public PopUpWindowAdapter(List<String> data) {
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

        TextView textView = (TextView)convertView.findViewById(R.id.title);
        textView.setText(data.get(i));


        return convertView;
    }
}
