package com.moible.qlf.mvpdemo.mvp.biz;

public interface IUserBiz {
    void login(String username,String password,OnLoginListener onLoginListener);
}
