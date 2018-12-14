package com.moible.qlf.mvpdemo.http;


import android.util.Log;

import rx.functions.Func1;


public class HttpResultFunc<T> implements Func1<BaseResponse<T>, T> {
    @Override
    public T call(BaseResponse<T> tBaseResponseBean) {
        if (tBaseResponseBean.getReturnCode() != 200){
            try {
                Log.i("TAG", "===call: " + tBaseResponseBean.getReturnData().toString());
                throw new ApiException(tBaseResponseBean.getReturnCode());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
        return tBaseResponseBean.getReturnData();
    }
}
