package com.moible.qlf.mvpdemo.bottomfragment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moible.qlf.baseframework.base.BaseFragment;
import com.moible.qlf.mvpdemo.R;

import butterknife.BindView;
import butterknife.Unbinder;

public class BottomFragment1 extends BaseFragment {

    Unbinder unbinder;

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bottom1;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}