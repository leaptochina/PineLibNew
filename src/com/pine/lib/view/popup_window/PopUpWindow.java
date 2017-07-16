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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 14/07/2017.
 */

public class PopUpWindow extends PineActivity implements AdapterView.OnItemClickListener {

    private TextView tvTitle;

    private ListView lists;


    private String title = "";
    private Boolean canBeCanceled = true;
    private Boolean singleChecked = true;
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
        singleChecked = intent.getBooleanExtra("singleChecked", true);
        callbackBroadcast = intent.getStringExtra("callbackBroadcast");
        values = intent.getStringArrayListExtra("values");
        key = intent.getStringArrayListExtra("key");

        tvTitle = (TextView)findViewById(R.id.title);
        lists = (ListView)findViewById(R.id.lists);

        tvTitle.setText(title);

        popUpWindowAdapter = new PopUpWindowAdapter(values);
        lists.setAdapter(popUpWindowAdapter);

        lists.setOnItemClickListener(this);

    }


    public static void show(Boolean canBeCanceled, Boolean singleChecked, String title, String callbackBroadcast, ArrayList<String> key, ArrayList<String> values) {
        Intent intent = new Intent(A.a(), PopUpWindow.class);
        intent.putExtra("canBeCanceled", canBeCanceled);
        intent.putExtra("singleChecked", singleChecked);
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
