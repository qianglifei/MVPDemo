package com.moible.qlf.mvpdemo.bottomfragment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moible.qlf.baseframework.base.BaseFragment;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.ui.LoginActivity;

import butterknife.BindView;
import butterknife.Unbinder;

public class BottomFragment1 extends BaseFragment{

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
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}