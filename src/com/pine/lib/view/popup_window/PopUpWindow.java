package com.pine.lib.view.popup_window;

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
import com.pine.lib.view.UIInject.UiInject;
import com.pine.lib.view.UIInject.interfaces.InjectView;

import org.w3c.dom.Text;

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
    private ArrayList<String> values = new ArrayList<String>();
    private ArrayList<String> key = new ArrayList<String>();

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
        values = intent.getStringArrayListExtra("values");
        key = intent.getStringArrayListExtra("key");

        tvTitle = (TextView)findViewById(R.id.title);
        lists = (ListView)findViewById(R.id.lists);
        cancel = (TextView)findViewById(R.id.cancel);
        done = (TextView)findViewById(R.id.done);


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

                BroadCastManager.i().send(callbackBroadcast, "0");
                finish();
            }
        });

        if (!canBeCanceled) cancel.setVisibility(View.GONE);
        if (!isDoneShow) done.setVisibility(View.GONE);

    }


    public static void show(Boolean canBeCanceled, Boolean isDoneShow, String title, String callbackBroadcast, ArrayList<String> key, ArrayList<String> values) {
        Intent intent = new Intent(A.a(), PopUpWindow.class);
        intent.putExtra("canBeCanceled", canBeCanceled);
        intent.putExtra("isDoneShow", isDoneShow);
        intent.putExtra("title", title);
        intent.putExtra("callbackBroadcast", callbackBroadcast);
        intent.putStringArrayListExtra("key", key);
        intent.putStringArrayListExtra("values", values);

        A.a().startActivity(intent);
        I.i(R.anim.in_from_down, R.anim.out_to_none);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        BroadCastManager.i().send(callbackBroadcast, key.get(i));
        finish();
    }
}
