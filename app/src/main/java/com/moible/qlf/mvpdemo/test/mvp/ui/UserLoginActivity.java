package com.moible.qlf.mvpdemo.test.mvp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moible.qlf.mvpdemo.main.MainActivity;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.test.mvp.presenter.UserLoginPresenter;
import com.moible.qlf.mvpdemo.test.mvp2.bean.Users;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserLoginActivity extends AppCompatActivity implements IloginView {
    @BindView(R.id.editText_userName)
    EditText editTextUserName;
    @BindView(R.id.editText_Password)
    EditText editTextPassword;
    @BindView(R.id.button_login)
    Button buttonLogin;
    private ProgressDialog mPbLoading;
    private Context mContext = this;
    private Unbinder unbinder;

    private UserLoginPresenter mULPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    public String getUserName() {
        return editTextUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public void showLoading() {
        mPbLoading = ProgressDialog.show(mContext,"提示：","正在登陆中，请稍后。。");
    }

    @Override
    public void hideLoading() {
        mPbLoading.dismiss();
    }

    @Override
    public void toMainActivity(Users user) {
        Intent intent = new Intent(mContext,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @OnClick(R.id.button_login)
    public void onViewClicked() {
        mULPresenter.login();
    }
}
