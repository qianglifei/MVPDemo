package com.moible.qlf.mvpdemo.login.mvp;

import android.content.Context;

import com.moible.qlf.baseframework.base.BaseModel;
import com.moible.qlf.baseframework.base.BasePresenter;
import com.moible.qlf.baseframework.base.BaseView;
import com.moible.qlf.baseframework.com.OnLoadDataIm;
import com.moible.qlf.mvpdemo.http.HttpResultFunc;
import com.moible.qlf.mvpdemo.http.ReturnDataBean;

import io.reactivex.Flowable;

public interface LoginContract {
    /**
     * V视图
     */
    interface  ILoginView extends BaseView {
        void setLoginData(Object object);
    }

    /**
     * M 视图
     */
    interface ILoginModel extends BaseModel {
        Flowable<HttpResultFunc<ReturnDataBean>> postSendLoginData(Context context, String userName, String password, OnLoadDataIm onLoadDataIm);
    }

    /**
     * P视图
     */
    abstract  class  ILoginPresenter extends BasePresenter<ILoginView,ILoginModel> {
        public abstract void postSendLoginData(Context context, String userName, String password, OnLoadDataIm onLoadDataIm);
    }
}
