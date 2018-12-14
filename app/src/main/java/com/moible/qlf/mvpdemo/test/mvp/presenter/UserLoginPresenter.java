package com.moible.qlf.mvpdemo.test.mvp.presenter;

import android.content.Context;
import android.os.Handler;

import com.moible.qlf.mvpdemo.test.mvp.biz.IUserBiz;
import com.moible.qlf.mvpdemo.test.mvp.biz.UserBiz;
import com.moible.qlf.mvpdemo.test.mvp.ui.IloginView;



/**
 * P层负责对View层和Model层之间的交互
 */
public class UserLoginPresenter {

    private Context mContext = null;
    private IUserBiz iUserBiz;
    private IloginView iloginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IloginView iloginView) {
        this.iloginView = iloginView;
        this.iUserBiz = new UserBiz();
    }

    public void login(){
        iloginView.showLoading();
//        iUserBiz.login(iloginView.getUserName(), iloginView.getPassword(), new OnLoginListener() {
//            @Override
//            public void OnLoginSuccess(final User user) {
//                //需要在ui线程执行
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        iloginView.hideLoading();
//                        iloginView.toMainActivity(user);
//                    }
//                });
//            }
//
//            @Override
//            public void OnLoginFailed() {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        iloginView.hideLoading();
//                        iloginView.showFailedError();
//                    }
//                });
//            }
//        });
    }

}
