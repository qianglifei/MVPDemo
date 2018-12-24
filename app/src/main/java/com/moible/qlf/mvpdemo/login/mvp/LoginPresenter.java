package com.moible.qlf.mvpdemo.login.mvp;

import android.content.Context;
import android.util.Log;

import com.moible.qlf.baseframework.baserx.RxSubscriber;
import com.moible.qlf.baseframework.com.OnLoadDataIm;
import com.moible.qlf.baseframework.utils.ToastUtils;


import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LoginPresenter extends LoginContract.ILoginPresenter{

    @Override
    public void postSendLoginData(Context context, RequestBody requestBody, OnLoadDataIm callback) {
        mModel.postSendLoginData(context,requestBody,callback).subscribe(new RxSubscriber<ResponseBody>(mContext,true) {
            @Override
            protected void _onNext(ResponseBody resultFunc) {
                try {
                    mView.setLoginData(resultFunc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void _onError(String message) {
                mView.stopLoading();
                ToastUtils.toastShort(mContext, message);
            }
        });
    }
}
