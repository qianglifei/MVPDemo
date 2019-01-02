package com.moible.qlf.baseframework.baserx;

import android.content.Context;

import com.moible.qlf.baseframework.R;
import com.moible.qlf.baseframework.dialog.LoadingDialog;
import com.moible.qlf.baseframework.utils.NetWorkUtils;

import io.reactivex.subscribers.DisposableSubscriber;


/********************使用例子********************/

/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new RxSubscriber<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }
@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/

public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog=true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog= true;
    }
    public void hideDialog() {
        this.showDialog= true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, context.getString(R.string.loading),true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, context.getString(R.string.loading),showDialog);
    }

    @Override
    public void onComplete() {
        if (showDialog){
            LoadingDialog.cancelLoadingDialog();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showLoadingDialog(mContext,0,msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        if (showDialog){
            LoadingDialog.cancelLoadingDialog();
        }

        if (!NetWorkUtils.isNetConnected(mContext)) {
            //网络
            _onError(mContext.getString(R.string.no_net));
        } else if (e instanceof ServerException) {
            //服务器
            _onError(e.getMessage());
        } else {
            //其它
            _onError(mContext.getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
