package com.moible.qlf.mvpdemo.mvp2.ui;

import com.moible.qlf.mvpdemo.mvp2.bean.Users;

public interface ILoginView {
    String getUserName();
    String getPassword();

    void showLoading();
    void hideLoading();

    void toMainActivity(Users users);
    void showFailedError();
}
