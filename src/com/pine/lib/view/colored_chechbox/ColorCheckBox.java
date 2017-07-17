package com.pine.lib.view.colored_chechbox;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pine.lib.R;
import com.pine.lib.base.activity.A;

/**
 * Created by ben on 17/07/2017.
 */

public class ColorCheckBox extends TextView implements View.OnClickListener {

    public int uncheckedBackgroundColor = R.drawable.circle_light_brown;
    public int uncheckedTextColor = A.c().getResources().getColor(R.color.white);
    public int checkedBackgroundColor = R.drawable.circle_orange;
    public int checkedTextColor = A.c().getResources().getColor(R.color.white);

    private Boolean isChoosed = false;

    public ColorCheckBox(Context context) {
        super(context);
        init();
    }

    public ColorCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnClickListener(this);

        refreshUI();
    }


    @Override
    public void onClick(View view) {
        isChoosed = !isChoosed;

        refreshUI();
    }

    public void refreshUI() {
        if (isChoosed) {
            setBackgroundResource(checkedBackgroundColor);
            setTextColor(checkedTextColor);
        }else
        {
            setBackgroundResource(uncheckedBackgroundColor);
            setTextColor(uncheckedTextColor);
        }
    }
}
