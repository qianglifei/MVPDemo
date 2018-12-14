package com.moible.qlf.mvpdemo.login.mvp;

import android.content.Context;

import com.moible.qlf.baseframework.baserx.RxSubscriber;
import com.moible.qlf.baseframework.com.OnLoadDataIm;
import com.moible.qlf.baseframework.utils.ToastUtils;
import com.moible.qlf.mvpdemo.http.BaseResponse;
import com.moible.qlf.mvpdemo.http.HttpResultFunc;
import com.moible.qlf.mvpdemo.http.ReturnDataBean;

public class LoginPresenter extends LoginContract.ILoginPresenter{

    @Override
    public void postSendLoginData(Context context, String userName, String password, OnLoadDataIm callback) {
        mModel.postSendLoginData(context,userName,password,callback).subscribe(new RxSubscriber<HttpResultFunc<ReturnDataBean>>(mContext,false) {
            @Override
            protected void _onNext(HttpResultFunc<ReturnDataBean> resultFunc) {
                mView.setLoginData(resultFunc);
            }

            @Override
            protected void _onError(String message) {
                mView.stopLoading();
                ToastUtils.toastShort(mContext, "加载失败");
            }
        });
    }
}
