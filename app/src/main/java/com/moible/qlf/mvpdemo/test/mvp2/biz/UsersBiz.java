package com.moible.qlf.mvpdemo.test.mvp2.biz;

import android.os.SystemClock;

import com.moible.qlf.mvpdemo.test.mvp.biz.OnLoginListener;
import com.moible.qlf.mvpdemo.test.mvp2.bean.Users;

public class UsersBiz implements IUsersBiz {

    @Override
    public void login(final String userName, final String password, final OnLoginListener listener) {
        //模仿网络请求
        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(1000);
                //登陆成功
                if (userName.equalsIgnoreCase("123") &&
                        password.equalsIgnoreCase("0")){

                    Users users = new Users();
                    users.setUserName(userName);
                    users.setPassword(password);
                    listener.OnLoginSuccess(users);
                }else {
                    listener.OnLoginFailed();
                }
            }
        }.start();
    }

}
