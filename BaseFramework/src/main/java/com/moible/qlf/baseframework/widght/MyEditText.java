
package com.moible.qlf.baseframework.widght;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.moible.qlf.baseframework.com.FontMain;


/**
 * 自定义edittext
 *
 * @author lml
 * @2015-9-8
 * @TODO
 * @MyButton
 */
public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    private Context mContext;

    private String TypefaceName = "msyh";

    public String getTypefaceName() {
        return TypefaceName;
    }

    public void setTypefaceName(Context context) {
        this.mContext = context;
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "font/" + TypefaceName
                + ".TTF");
        this.setTypeface(typeface);
        System.gc();
    }

    public MyEditText(Context context) {
        super(context);
        init(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyEditText(Context context, AttributeSet attrs, int defSyle) {
        super(context, attrs, defSyle);
        init(context);
    }

    /***
     * 设置字体
     *
     * @return
     */
    public void init(Context context) {
        setTypeface(FontMain.setFont(context));
    }

}
