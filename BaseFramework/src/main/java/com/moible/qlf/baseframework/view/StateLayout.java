package com.moible.qlf.baseframework.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.moible.qlf.baseframework.R;


/**
 * 封装了4种状态的View：正在加载、加载失败、没有数据、正常界面
 * @author dzl
 *
 */
public class StateLayout extends FrameLayout {
    private View loadingView;
    private View failView;
    private View emptyView;
    private View contentView;
    private ImageView loading_View;
    private AnimationDrawable mAnimationDrawable;

    /**
     * 包含3种状态(正在加载，加载失败，没有数据)的一个容器
     */

    private FrameLayout container;

    public StateLayout(@NonNull Context context) {
        super(context);
        //创建出来的容器已经包含了3中状态：正在加载，加载失败，没有数据
        container = (FrameLayout) View.inflate(getContext(),R.layout.state_layout,null);

        loadingView = container.findViewById(R.id.loadingView);
        loading_View = loadingView.findViewById(R.id.iv_loading);
        mAnimationDrawable = (AnimationDrawable) loading_View.getDrawable();

        failView =  container.findViewById(R.id.failView);
        emptyView = container.findViewById(R.id.emptyView);

        showLoadingView();

        addView(container);
    }

    /**
     * 显示正在加载中
     */
    private void showLoadingView() {
        mAnimationDrawable.start();
        showView(loadingView);
    }

    /**
     * 显示加载失败
     */
    public void showFailView(){
        showView(failView);
        mAnimationDrawable.stop();
    }

    /**
     * 显示加载为空
     */
    public void EmptyView(){
        showView(emptyView);
    }

    public void setContentView(int layoutResId){
        View content = View.inflate(getContext(),layoutResId,null);
        this.setContentView(content);
    }

    public void setContentView(View contentView){
        this.contentView = contentView;
        container.addView(contentView);
        //默认不显示，默认显示的是LoadingView
        contentView.setVisibility(View.GONE);
    }

    /**
     * 显示制定的View。并隐藏其他的View
     * @param view
     */
    private void showView(View view) {
        for (int i = 0; i < container.getChildCount() ; i++) {
            View child = container.getChildAt(i);
            child.setVisibility(child == view ? View.VISIBLE: View.GONE);
        }
    }

}
