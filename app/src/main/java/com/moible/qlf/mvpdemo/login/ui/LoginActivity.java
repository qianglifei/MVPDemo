package com.moible.qlf.mvpdemo.login.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.moible.qlf.baseframework.base.BaseActivity;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.mvp.LoginContract;
import com.moible.qlf.mvpdemo.login.mvp.LoginModule;
import com.moible.qlf.mvpdemo.login.mvp.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModule> implements LoginContract.ILoginView {

    @Override
    protected void initEvent() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
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

    @Override
    public void setLoginData(Object object) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void fail(Object ob) {

    }
}
