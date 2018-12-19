package com.moible.qlf.mvpdemo.login.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moible.qlf.baseframework.base.BaseActivity;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.mvp.LoginContract;
import com.moible.qlf.mvpdemo.login.mvp.LoginModule;
import com.moible.qlf.mvpdemo.login.mvp.LoginPresenter;
import com.moible.qlf.mvpdemo.util.StatusBarUtil;
import com.moible.qlf.mvpdemo.util.ToastUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModule> implements LoginContract.ILoginView {
    @BindView(R.id.editText_userName)
    EditText editTextUserName;
    @BindView(R.id.editText_Password)
    EditText editTextPassword;
    @BindView(R.id.button_logins)
    Button buttonLogins;
    private Context mContext = null;
    private Unbinder unbinder = null;

    @Override
    protected void modifyStatusBarColor() {
        StatusBarUtil.setWindowStatusBarColor(this,Color.BLUE);
    }

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
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        mContext = this;
        unbinder = ButterKnife.bind(this);
        buttonLogins.setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.button_logins:
                login();
                break;
        }
    }

    private void login() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("yhzh",editTextUserName.getText().toString().trim());
        hashMap.put("yhmm",editTextPassword.getText().toString().trim());
        String obj = new JSONObject(hashMap).toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),obj);
        mPresenter.postSendLoginData(mContext, body, null);
    }

    @Override
    public void setLoginData(Object object) throws IOException {
        ResponseBody responseBody = (ResponseBody) object;
        ToastUtils.showToast(mContext,responseBody.string());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder == null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
