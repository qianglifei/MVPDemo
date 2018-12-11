package com.moible.qlf.mvpdemo.mvp.ui;

import com.moible.qlf.mvpdemo.mvp2.bean.Users;

public interface IloginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(Users user);

    void showFailedError();

}
