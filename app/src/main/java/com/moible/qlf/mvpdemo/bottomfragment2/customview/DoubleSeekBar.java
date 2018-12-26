package com.moible.qlf.mvpdemo.bottomfragment2.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DoubleSeekBar extends SurfaceView {
    /**
     *SurfaceView里面有一个getHolder的方法，我们可以获取一个SurfaceHolder，
     * 通过SurfaceHolder我们可以监听SurfaceView的生命周期以及获取Canvas对象
     */
    private SurfaceHolder mSufaceHolder;
    /**
     * 与SurfaceHolder绑定的对象
     */
    private Canvas mCanvas;
    /**
     * 用于绘制线程
     */
    private Thread t;
    /**
     * 线程的控制开关
     */
    private boolean isRunning = false;

    public DoubleSeekBar(Context context) {
        super(context);
    }

    public DoubleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
