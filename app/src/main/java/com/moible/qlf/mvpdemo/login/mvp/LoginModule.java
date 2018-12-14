package com.moible.qlf.mvpdemo.login.mvp;


import android.content.Context;

import com.moible.qlf.baseframework.com.OnLoadDataIm;
import com.moible.qlf.mvpdemo.http.HttpResultFunc;
import com.moible.qlf.mvpdemo.http.ReturnDataBean;

import io.reactivex.Flowable;

public class LoginModule implements  LoginContract.ILoginModel{


    @Override
    public Flowable<HttpResultFunc<ReturnDataBean>> postSendLoginData(Context context, String userName, String password, OnLoadDataIm onLoadDataIm) {
        return null;
    }
}
