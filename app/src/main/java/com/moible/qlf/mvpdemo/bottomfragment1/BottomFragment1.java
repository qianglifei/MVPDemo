package com.moible.qlf.mvpdemo.bottomfragment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.moible.qlf.baseframework.base.BaseFragment;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.customview.LoadingFrame;
import com.moible.qlf.mvpdemo.login.customview.StateLayout;
import com.moible.qlf.mvpdemo.login.ui.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomFragment1 extends BaseFragment {
    Unbinder unbinder;
//    @BindView(R.id.button_login)
//    Button buttonLogin;

    Unbinder unbinder1;

    private LoadingFrame loadingFrame;

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        StateLayout stateLayout = new StateLayout(getContext());

        stateLayout.showEmptyView();

        stateLayout.setOnReloadListener(new StateLayout.OnReloadListener() {
            @Override
            public void onReload() {

            }
        });

        return R.layout.fragment_bottom1;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder1.unbind();
    }

}