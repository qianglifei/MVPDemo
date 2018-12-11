package com.moible.qlf.mvpdemo.mvp2.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moible.qlf.mvpdemo.MainActivity;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.mvp.ui.IloginView;
import com.moible.qlf.mvpdemo.mvp2.bean.Users;
import com.moible.qlf.mvpdemo.mvp2.presenter.UsersPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author qlf
 */
public class LoginActivity extends AppCompatActivity implements IloginView {

    @BindView(R.id.editText_userName)
    EditText editTextUserName;
    @BindView(R.id.editText_Password)
    EditText editTextPassword;
    @BindView(R.id.button_logins)
    Button buttonLogins;
    private Unbinder unbinder;
    private Context mContext = this;
    private ProgressDialog mProgressDialog;

    private UsersPresenter mUsersPresenter = new UsersPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        mProgressDialog = ProgressDialog.show(mContext,"温馨提示：","正在登陆请稍后。。。");
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
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

    @OnClick(R.id.button_logins)
    public void onViewClicked() {
        mUsersPresenter.login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }
}
