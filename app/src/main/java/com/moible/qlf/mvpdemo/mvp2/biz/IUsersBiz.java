package com.moible.qlf.mvpdemo.mvp2.biz;

import com.moible.qlf.mvpdemo.mvp.biz.OnLoginListener;

public interface IUsersBiz {
    void login(String userName, String password, OnLoginListener listener);
}
