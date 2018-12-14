package com.moible.qlf.mvpdemo.test.mvp2.biz;

import com.moible.qlf.mvpdemo.test.mvp2.bean.Users;

public interface OnLoginLisenter {
    void loginSuccess(Users users);
    void loginFailed(String causeOfFailure);
}
