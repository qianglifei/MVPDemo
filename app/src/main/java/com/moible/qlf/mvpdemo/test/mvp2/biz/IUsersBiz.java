package com.moible.qlf.mvpdemo.test.mvp2.biz;

import com.moible.qlf.mvpdemo.test.mvp.biz.OnLoginListener;

public interface IUsersBiz {
    void login(String userName, String password, OnLoginListener listener);
}
