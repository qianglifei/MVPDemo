package com.moible.qlf.mvpdemo.login.mvp;


import android.content.Context;

import com.moible.qlf.baseframework.baserx.RxSchedulers;
import com.moible.qlf.baseframework.com.OnLoadDataIm;
import com.moible.qlf.mvpdemo.api.RetrofitApiService;
import com.moible.qlf.mvpdemo.http.RetrofitManager;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class LoginModule implements  LoginContract.ILoginModel{

    @Override
    public Flowable<ResponseBody> postSendLoginData(Context context, RequestBody requestBody, OnLoadDataIm onLoadDataIm) {
        return RetrofitManager.
                getInstance().
                createReq(RetrofitApiService.class).
                loginData(requestBody).
                compose(RxSchedulers.<ResponseBody>io_main());
    }
}
