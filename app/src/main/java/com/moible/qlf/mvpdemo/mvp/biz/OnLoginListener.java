package com.moible.qlf.mvpdemo.mvp.biz;

import com.moible.qlf.mvpdemo.mvp2.bean.Users;

public interface OnLoginListener {
    void OnLoginSuccess(Users user);
    void OnLoginFailed();
}
