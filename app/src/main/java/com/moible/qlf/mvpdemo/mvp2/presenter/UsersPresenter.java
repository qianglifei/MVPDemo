package com.moible.qlf.mvpdemo.mvp2.presenter;

import android.os.Handler;

import com.moible.qlf.mvpdemo.mvp.biz.OnLoginListener;
import com.moible.qlf.mvpdemo.mvp.ui.IloginView;
import com.moible.qlf.mvpdemo.mvp2.bean.Users;
import com.moible.qlf.mvpdemo.mvp2.biz.IUsersBiz;
import com.moible.qlf.mvpdemo.mvp2.biz.UsersBiz;

public class UsersPresenter {
    private IloginView iloginView;
    private Handler mHandler = new Handler();
    private IUsersBiz iUsersBiz;

    public UsersPresenter(IloginView iloginView) {
        this.iloginView = iloginView;
        iUsersBiz = new UsersBiz();
    }

    public void login(){
        iloginView.showLoading();
        iUsersBiz.login(iloginView.getUserName(), iloginView.getPassword(), new OnLoginListener() {
            @Override
            public void OnLoginSuccess(final Users user) {
                //更新UI
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iloginView.hideLoading();
                        iloginView.toMainActivity(user);
                    }
                });
            }

            @Override
            public void OnLoginFailed() {
                //更新UI
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iloginView.hideLoading();
                        iloginView.showFailedError();
                    }
                });
            }
        });
    }
}
