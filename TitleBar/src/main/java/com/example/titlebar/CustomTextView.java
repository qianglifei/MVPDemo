package com.example.titlebar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;

    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    public interface DrawableLeftListener {
        void onDrawableLeftClick(View view);
    }

    public interface DrawableRightListener {
        void onDrawableRightClick(View view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mRightListener != null){
                    //getCompoundDrawables()[DRAWABLE_RIGHT] 设置文本框中图片的位置
                    //setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom)
                    //0，1，2，3
                    Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
                    if(drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width())){
                        mRightListener.onDrawableRightClick(this);
                        return true;
                    }
                }

                if (mLeftListener != null){
                    Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT];
                    if (drawableLeft != null && event.getRawX() <= (getRight()-drawableLeft.getBounds().width())){
                        mLeftListener.onDrawableLeftClick(this);
                        return true;
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
