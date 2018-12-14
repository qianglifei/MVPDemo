package com.moible.qlf.mvpdemo.test.mvp.biz;

import com.moible.qlf.mvpdemo.test.mvp2.bean.Users;

public interface OnLoginListener {
    void OnLoginSuccess(Users user);
    void OnLoginFailed();
}
