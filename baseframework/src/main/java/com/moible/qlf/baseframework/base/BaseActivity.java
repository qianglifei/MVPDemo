package com.moible.qlf.baseframework.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moible.qlf.baseframework.R;
import com.moible.qlf.baseframework.baserx.RxManager;
import com.moible.qlf.baseframework.com.Constants;
import com.moible.qlf.baseframework.dialog.LoadingDialog;
import com.moible.qlf.baseframework.utils.ASimpleCacheUtils;
import com.moible.qlf.baseframework.utils.ExitUtil;
import com.moible.qlf.baseframework.utils.SharedPreferencesUtil;
import com.moible.qlf.baseframework.utils.StringUtil;
import com.moible.qlf.baseframework.utils.TUtil;
import com.moible.qlf.baseframework.utils.ToastUtils;
import com.moible.qlf.baseframework.utils.daynightutils.ChangeModeController;
import com.moible.qlf.baseframework.widght.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author qlf
 * 1.E,T,K,V等表示固定泛型类型参数需要声明，而通配符?不需要定义可以直接使用，表示泛型的类型参数。
 * 2.E,T,K,V等表示固定泛型类型这样可以在方法里来定义入参，而通配符?做不到的。
 * 例子：
 *
 * public void add(List list,T a){
 * list.add(a);
 *
 * }
 * 3.泛型类型参数可以申明在方法返回值之前作用域为该方法，
 * 也可以申明在类后面这样作用域为整个类(如果只申明在方法的话，有可能放进去的是猪，取出来的是狗)。
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends SupportActivity implements View.OnClickListener {
    public String TAG, urlStr, device_token;
    public Context mContext;
    public ASimpleCacheUtils aSimpleCacheUtils;
    private long firstTime, spaceTime, secondTime;
    public TextView title;
    public T mPresenter;
    public E mModel;
    public LinearLayout back_layout, centertitle_layout;
    public ImageView mLeftOne, mLeftTwo, mleftThree, mRightTwo, mRightThree, mCenterone, image_view, mCentertwo, iv_record;
    public TextView mLeftThree, mRightOne, mCenterTitle, mtv_context, load_context, mtv_load;
    public RelativeLayout rl_layout;
    private FrameLayout rl_dialog, not_view;
    public RxManager mRxManager;
    private Unbinder bind;
    public Activity mActivity;
    public String imageType = "phone";
    private int quanPing = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mRxManager = new RxManager();
        TAG = getClass().getSimpleName();
        aSimpleCacheUtils = new ASimpleCacheUtils(mContext);
        ExitUtil.getInstance().addActivity(this);
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);

        setContentView(getLayoutId());

        //userid
        Constants.user_id = SharedPreferencesUtil.getPrefString(mContext,
                "userId", "");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.toolsbarColor);//通知栏所需颜色
//        }
        bind = ButterKnife.bind(this);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initView();
        processLogic(savedInstanceState);
        initPresenter();
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
    }

    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 子类实现
     * 获取布局文件
     */
    public abstract int getLayoutId();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    /**
     * 初始化view
     *
     */
    public abstract void initView();

    /**
     * 初始化view控件 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 初始化标题栏
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-29 11:18
     * @author lml
     */
    public void initTitle() {
        back_layout = (LinearLayout) findViewById(R.id.ll_return);
        centertitle_layout = (LinearLayout) findViewById(R.id.ll_centertitle);
        rl_layout = (RelativeLayout) findViewById(R.id.rl_title);
        mCenterTitle = (TextView) findViewById(R.id.tv_centertitle);
        mLeftOne = (ImageView) findViewById(R.id.iv_leftone);
        mLeftTwo = (ImageView) findViewById(R.id.iv_lefttwo);
        mleftThree = (ImageView) findViewById(R.id.iv_leftthree);
        mLeftThree = (TextView) findViewById(R.id.tv_leftthree);
        mRightOne = (TextView) findViewById(R.id.tv_rightone);
        mRightTwo = (ImageView) findViewById(R.id.iv_righttwo);
        mRightThree = (ImageView) findViewById(R.id.iv_rightthree);
        mCenterone = (ImageView) findViewById(R.id.iv_centerone);
        mCentertwo = (ImageView) findViewById(R.id.iv_centertwo);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 双击退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String currentActivityName = this.getClass().getSimpleName();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
                && currentActivityName.equals("MapActivity")) {
            firstTime = System.currentTimeMillis();
            spaceTime = firstTime - secondTime;
            secondTime = firstTime;
            if (spaceTime > 2000) {
                ToastUtils.toastShort(mContext, "点击第二次退出");
                return false;
            } else {
//                Intent i = new Intent(Intent.ACTION_MAIN);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addCategory(Intent.CATEGORY_HOME);
//                startActivity(i);
                ExitUtil.getInstance().exit();
            }
        }

//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            finish();
//        }
        return super.onKeyDown(keyCode, event);
    }

    public static DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    public void hideBgTitle() {

        if (rl_layout != null) {
            rl_layout.setVisibility(View.GONE);
        }

    }

    public void showBgTitle() {

        if (rl_layout != null) {
            rl_layout.setVisibility(View.VISIBLE);
        }

    }

    public void setCTitle(String title) {
        if (StringUtil.isNotNull(title)) {
            mCenterTitle.setText(title);
            mCenterTitle.setVisibility(View.VISIBLE);
            mCenterone.setVisibility(View.GONE);
            mCentertwo.setVisibility(View.GONE);
        }

    }

    /**
     * 初始化正在加载页
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 14:29
     * @author lml
     */
    public void initLoadView() {
        try {
            rl_dialog = (FrameLayout) findViewById(R.id.rl_dialog);
            image_view = (ImageView) findViewById(R.id.loadingImageView);
            load_context = (TextView) findViewById(R.id.id_tv_loadingmsg);
        } catch (Exception e) {
            ToastUtils.toastShort(mContext, "加载布局失败");
        }
    }

    /**
     * 显示正在加载页
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-29 11:16
     * @author lml
     */
    public void showLoadView() {
        if (rl_dialog != null) {
            rl_dialog.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 隐藏正在加载页
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-29 11:16
     * @author lml
     */
    public void hideLoadView() {
        if (rl_dialog != null) {
            rl_dialog.setVisibility(View.GONE);
        }

    }

    /**
     * 初始化没有内容页
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 14:28
     * @author lml
     */
    public void initNotView() {
        not_view = (FrameLayout) findViewById(R.id.ll_not_view);
        mtv_context = (TextView) findViewById(R.id.mtv_context);
        mtv_load = (TextView) findViewById(R.id.mtv_load);
        iv_record = (ImageView) findViewById(R.id.iv_record);
    }

    /**
     * 显示没有内容页
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 14:28
     * @author lml
     */
    public void showNotContext(String str) {
        not_view.setVisibility(View.VISIBLE);
        mtv_context.setText(str);
    }

    /**
     * 隐藏没有内容页
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 14:28
     * @author lml
     */
    public void hideNotContext() {
        not_view.setVisibility(View.GONE);
        mtv_context.setText("暂无内容");
    }

    public void setUrlStr(String title) {
        urlStr = title;

    }

    /**
     * 左边箭头
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 15:02
     * @author lml
     */
    public void setLeftOneBg(int resId) {
        if (resId == 0) {
            mLeftOne.setVisibility(View.GONE);
        } else {
            mLeftOne.setVisibility(View.VISIBLE);
        }
        mLeftOne.setImageResource(resId);
        mLeftOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    /**
     * 左边图片
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 15:02
     * @author lml
     */
    public void setLeftTwoBg(int resId) {
        if (resId == 0) {
            mLeftTwo.setVisibility(View.GONE);
        } else {
            mLeftTwo.setVisibility(View.VISIBLE);
        }
        mLeftTwo.setImageResource(resId);
        mLeftTwo.setOnClickListener(this);
    }

    /**
     * 左边图片
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 15:02
     * @author lml
     */
    public void setLeftThreeBg(int resId) {
        if (resId == 0) {
            mleftThree.setVisibility(View.GONE);
        } else {
            mleftThree.setVisibility(View.VISIBLE);
        }
        mleftThree.setImageResource(resId);
        mleftThree.setOnClickListener(this);
    }

    public void setCenteroneBg(int resId) {
        if (resId == 0) {
            mCenterone.setVisibility(View.GONE);
        } else {
            mCenterone.setVisibility(View.VISIBLE);
        }
        mCenterone.setImageResource(resId);
        centertitle_layout.setOnClickListener(this);
    }

    public void setCentertwoBg(int resId) {

        mCenterTitle.setVisibility(View.GONE);
        if (resId == 0) {
            mCentertwo.setVisibility(View.GONE);
        } else {
            mCentertwo.setVisibility(View.VISIBLE);
        }

        mCentertwo.setImageResource(resId);
        mCentertwo.setOnClickListener(this);
    }

    public void setLeftThreeText(String content) {
        if (StringUtil.isNotNull(content)) {
            mLeftThree.setVisibility(View.VISIBLE);
            mLeftThree.setText(content);
            mLeftThree.setOnClickListener(this);
        }
    }

    public void setRightOneText(String content) {
        if (StringUtil.isNotNull(content)) {
            mRightOne.setVisibility(View.VISIBLE);
            mRightOne.setText(content);
            mRightOne.setOnClickListener(this);
        }
    }

    public void setRightTwoBg(int resId) {
        if (resId == 0) {
            mRightTwo.setVisibility(View.GONE);
        } else {
            mRightTwo.setVisibility(View.VISIBLE);
        }
        mRightTwo.setImageResource(resId);
        mRightTwo.setOnClickListener(this);
    }

    public void setRightThreeBg(int resId, String type) {
        imageType = type;
        if (resId == 0) {
            mRightThree.setVisibility(View.GONE);
        } else {
            mRightThree.setVisibility(View.VISIBLE);
        }
        mRightThree.setImageResource(resId);
        mRightThree.setOnClickListener(this);
    }


    public void setRightThreeBg(int resId) {
        if (resId == 0) {
            mRightThree.setVisibility(View.GONE);
        } else {
            mRightThree.setVisibility(View.VISIBLE);
        }
        mRightThree.setImageResource(resId);
        mRightThree.setOnClickListener(this);
    }

    /**
     * 标题栏右边第三个
     *
     * @Description:
     * @ClassName:BaseActivity
     * @date 2016-7-5 14:31
     * @author lml
     */
    public void setRightThreeBgVisible(int resId) {
        if (resId != 0) {
            mRightThree.setVisibility(View.VISIBLE);
        } else {
            mRightThree.setVisibility(View.GONE);
        }
    }

    // 不随系统大小而改变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
    /**
     * @param clazz
     * @param bundle 跳转页面
     *
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz) {
        readyGo(clazz, null);
    }

    /**
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        readyGo(clazz, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 显示默认加载动画 默认加载文字
     */
    protected void showLoadingDialog() {
        LoadingDialog.showLoadingDialog(this);
    }

    /**
     * 取消加载动画
     */
    protected void cancelLoadingDialog() {
        LoadingDialog.cancelLoadingDialog();
    }


    /**
     * 设置主题
     */
    private void initTheme() {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mRxManager.clear();
        bind.unbind();
        AppManager.getAppManager().finishActivity(this);
        ExitUtil.getInstance().removeActivity(this);
    }
}
