package com.moible.qlf.baseframework.com;


/**
 *@className OnLoadDataIm
 *@描述
 *@作者lml
 *@日期2017/3/28 16:18
 *@修改日期
 *@版本
 */
public interface OnLoadDataIm<T> {
    void onSuccess(T t, int resultCode);
    void onSuccess(T t, String message, int resultCode);
    void onFailure(String msg, int resultCode);
}
