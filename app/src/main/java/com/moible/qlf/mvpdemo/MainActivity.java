package com.moible.qlf.mvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.moible.qlf.baseframework.base.BaseActivity;

/**
 * 类名后面加Biz一般写业务逻辑处理类
 * 类名后面加Dao一般是写数据访问类
 * business
 * Data Access Objects
 *
 * biz、biz.imp主要是写业务逻辑接口及实现，其中biz一般是接口或超类定义层，imp是具体实现层。
 * @author qlf
 *
 */
public class MainActivity extends BaseActivity {
    private Context mContext = this;

    @Override
    protected void initEvent() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }
}
