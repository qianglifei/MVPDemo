package com.moible.qlf.mvpdemo.login.mvp;

import android.content.Context;

import com.moible.qlf.baseframework.base.BaseModel;
import com.moible.qlf.baseframework.base.BasePresenter;
import com.moible.qlf.baseframework.base.BaseView;
import com.moible.qlf.baseframework.com.OnLoadDataIm;

import java.io.IOException;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface LoginContract {
    /**
     * V视图
     */
    interface  ILoginView extends BaseView {
        void setLoginData(Object object) throws IOException;
    }

    /**
     * M 视图
     */
    interface ILoginModel extends BaseModel {
        Flowable<ResponseBody> postSendLoginData(Context context, RequestBody requestBody, OnLoadDataIm onLoadDataIm);
    }

    /**
     * P视图
     */
    abstract  class  ILoginPresenter extends BasePresenter<ILoginView,ILoginModel> {
        public abstract void postSendLoginData(Context context, RequestBody requestBody, OnLoadDataIm onLoadDataIm);
    }
}
