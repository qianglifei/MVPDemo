package com.moible.qlf.mvpdemo.test.mvp.biz;

import com.moible.qlf.mvpdemo.test.mvp.bean.User;

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener) {
        //模拟子线程的耗时操作
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登陆成功
                if (username.equalsIgnoreCase("123") && password.equalsIgnoreCase("0")){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassdword(password);
                   // onLoginListener.OnLoginSuccess(user);
                }else {
                    onLoginListener.OnLoginFailed();
                }
            }
        }.start();

    }
}
