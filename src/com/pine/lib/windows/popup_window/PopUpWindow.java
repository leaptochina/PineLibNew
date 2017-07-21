package com.pine.lib.windows.popup_window;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pine.lib.R;
import com.pine.lib.base.activity.A;
import com.pine.lib.base.activity.PineActivity;
import com.pine.lib.func.broadcast.BroadCastManager;
import com.pine.lib.func.intentcall.I;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 14/07/2017.
 */

public class PopUpWindow extends PineActivity implements AdapterView.OnItemClickListener {

    private TextView tvTitle;

    private ListView lists;


    private String title = "";
    private TextView cancel = null;
    private TextView done = null;
    private Boolean canBeCanceled = true;
    private Boolean isDoneShow = true;
    private String callbackBroadcast = "";
    private List<PopupBean> values = new ArrayList<PopupBean>();


    private PopUpWindowAdapter popUpWindowAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        canBeCanceled = intent.getBooleanExtra("canBeCanceled", true);
        isDoneShow = intent.getBooleanExtra("isDoneShow", true);
        callbackBroadcast = intent.getStringExtra("callbackBroadcast");
        values = (List<PopupBean>) intent.getSerializableExtra("values");


        tvTitle = (TextView) findViewById(R.id.title);
        lists = (ListView) findViewById(R.id.lists);
        cancel = (TextView) findViewById(R.id.cancel);
        done = (TextView) findViewById(R.id.done);


        tvTitle.setText(title);

        popUpWindowAdapter = new PopUpWindowAdapter(values);
        lists.setAdapter(popUpWindowAdapter);

        lists.setOnItemClickListener(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BroadCastManager.i().send(callbackBroadcast, "");
                finish();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = "";
                for(PopupBean vs : values)
                {
                    if (vs.checked)
                    {
                        r += vs.key + ",";
                    }
                }
                if (r.length() > 0) r = r.substring(0, r.length() - 1);
                BroadCastManager.i().send(callbackBroadcast, r);
                finish();
            }
        });

        if (!canBeCanceled) cancel.setVisibility(View.GONE);
        if (!isDoneShow) done.setVisibility(View.GONE);

    }


    public static void show(Boolean canBeCanceled, Boolean isDoneShow, String title, String callbackBroadcast, List<PopupBean> values) {
        Intent intent = new Intent(A.a(), PopUpWindow.class);
        intent.putExtra("canBeCanceled", canBeCanceled);
        intent.putExtra("isDoneShow", isDoneShow);
        intent.putExtra("title", title);
        intent.putExtra("callbackBroadcast", callbackBroadcast);
        intent.putExtra("values", (Serializable) values);


        A.a().startActivity(intent);
        I.i(R.anim.in_from_down, R.anim.out_to_none);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (isDoneShow) {
            values.get(i).checked = ! values.get(i).checked;
            popUpWindowAdapter.notifyDataSetChanged();

        } else {
            BroadCastManager.i().send(callbackBroadcast, values.get(i).key);
            finish();
        }
    }
}
