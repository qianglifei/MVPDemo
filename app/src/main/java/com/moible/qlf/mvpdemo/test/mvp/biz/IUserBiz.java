package com.moible.qlf.mvpdemo.test.mvp.biz;

public interface IUserBiz {
    void login(String username,String password,OnLoginListener onLoginListener);
}
