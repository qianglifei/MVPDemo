package com.moible.qlf.mvpdemo.bottomfragment1;

import android.os.Bundle;

import com.moible.qlf.baseframework.base.BaseFragment;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.customview.LoadingFrame;

public class BottomFragment1 extends BaseFragment {
//    Unbinder unbinder;
//    @BindView(R.id.button_login)
//    Button buttonLogin;
//    Unbinder unbinder1;

    private LoadingFrame loadingFrame;

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
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });

        rootView.showFailView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}