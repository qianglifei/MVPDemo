package com.moible.qlf.baseframework.base;


public interface BaseView {
    /*******
     * 内嵌加载
     *******/

    void showLoading(String title);

    void stopLoading();

    void showMessage(String msg);
    void fail(Object ob);
}
