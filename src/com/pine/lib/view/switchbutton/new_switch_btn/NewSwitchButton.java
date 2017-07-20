package com.pine.lib.view.switchbutton.new_switch_btn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pine.lib.R;
import com.pine.lib.func.debug.G;
import com.pine.lib.view.fasttoast.T;

import org.w3c.dom.Text;

public class NewSwitchButton extends RelativeLayout implements View.OnClickListener, Animation.AnimationListener {
    private static G g = new G(NewSwitchButton.class);

    private ImageView stick;
    private Boolean isInit = false;
    private Boolean isLeft = true;
    private SwitchListener listener;

    private void init(Context context) {
        if (!isInit) {
            isInit = true;

            View c = View.inflate(context, R.layout.newswitchbtn_layout, this);
            setIsLeft(true);

            stick = (ImageView) findViewById(R.id.stick);

            setOnClickListener(this);
        }
    }

    public void setOnSwitchListener(SwitchListener listener) {
        this.listener = listener;
    }

    public NewSwitchButton(Context context) {
        super(context, null);
        init(context);
    }

    public NewSwitchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NewSwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public NewSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setIsLeft(Boolean isLeft) {
        if (!this.isLeft) {
            animation(isLeft, 0);
        }
        if (listener != null)
        {
            listener.onChange(isLeft, true);
        }
    }

    @Override
    public void onClick(View view) {

        animation(isLeft, 200);
        isLeft = !isLeft;

        if (listener != null)
        {
            listener.onChange(isLeft, false);
        }
    }

    private void animation(Boolean toLeft, int time) {
        TranslateAnimation myAnimation_Translate;
        if (toLeft) {
            myAnimation_Translate = new TranslateAnimation(0, getWidth() - getHeight(), 0, 0);
        } else {
            myAnimation_Translate = new TranslateAnimation(getWidth() - getHeight(), 0, 0, 0);
        }
        myAnimation_Translate.setDuration(time);
        myAnimation_Translate.setAnimationListener(this);
        myAnimation_Translate.setFillAfter(true);
        stick.startAnimation(myAnimation_Translate);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}