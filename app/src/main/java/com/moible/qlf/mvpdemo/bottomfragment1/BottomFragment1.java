package com.moible.qlf.mvpdemo.bottomfragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.moible.qlf.baseframework.base.BaseFragment;
import com.moible.qlf.mvpdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomFragment1 extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.button_login)
    Button buttonLogin;
    Unbinder unbinder1;

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
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}