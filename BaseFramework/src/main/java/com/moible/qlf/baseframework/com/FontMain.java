
package com.moible.qlf.baseframework.com;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 字体设置
 * 
 * @2015-9-6
 * @TODO
 * @FontMain
 * @author lml
 */
public class FontMain {
    static String fongUrl = "font/msyh.ttf";

    static Typeface tf;

    /***
     * 设置字体
     * 
     * @return
     */
    public static Typeface setFont(Context context) {
        if (tf == null) {
            tf = Typeface.createFromAsset(context.getAssets(), fongUrl);
        }
        return tf;
    }
}
