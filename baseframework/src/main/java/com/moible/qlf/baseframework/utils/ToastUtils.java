
package com.moible.qlf.baseframework.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.moible.qlf.baseframework.R;


/**
 * 吐司工具类
 */
public class ToastUtils {

    public static void toastShort(Context context, String msg) {
        try {
            View toastView = LayoutInflater.from(context).inflate(R.layout.app_view_toast, null);
            TextView mTvToast = (TextView) toastView.findViewById(R.id.tv_toast);
            mTvToast.setText(msg);
            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastView);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toastLong(Context context, String msg) {
        try {
            View toastView = LayoutInflater.from(context).inflate(R.layout.app_view_toast, null);
            TextView mTvToast = (TextView) toastView.findViewById(R.id.tv_toast);
            mTvToast.setText(msg);
            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastView);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
