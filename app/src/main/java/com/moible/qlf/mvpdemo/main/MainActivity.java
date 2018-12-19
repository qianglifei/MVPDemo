package com.moible.qlf.mvpdemo.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.moible.qlf.baseframework.base.BaseActivity;
import com.moible.qlf.baseframework.base.BaseModel;
import com.moible.qlf.baseframework.base.BasePresenter;
import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.bottomfragment1.BottomFragment1;
import com.moible.qlf.mvpdemo.bottomfragment2.BottomFragment2;
import com.moible.qlf.mvpdemo.bottomfragment3.BottomFragment3;
import com.moible.qlf.mvpdemo.bottomfragment4.BottomFragment4;
import com.moible.qlf.mvpdemo.util.StatusBarUtil;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类名后面加Biz一般写业务逻辑处理类
 * 类名后面加Dao一般是写数据访问类
 * business
 * Data Access Objects
 * <p>
 * biz、biz.imp主要是写业务逻辑接口及实现，其中biz一般是接口或超类定义层，imp是具体实现层。
 *
 * @author qlf
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.layFrame)
    FrameLayout layFrame;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBotNavBar;

    private BottomFragment1 bottomFragment1;
    private BottomFragment2 bottomFragment2;
    private BottomFragment3 bottomFragment3;
    private BottomFragment4 bottomFragment4;
    private FragmentManager fm = null;

    @Override
    protected void modifyStatusBarColor() {
        StatusBarUtil.setWindowStatusBarColor(this,Color.GREEN);
    }

    @Override
    protected void initEvent() {
        mBotNavBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBotNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //配置tab与之对应的fragment
        mBotNavBar.addItem(new BottomNavigationItem(R.mipmap.bottom_sy, "网络框架"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_jl, "自定义View"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_zw, "职位"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_zx, "资讯"));
        mBotNavBar.setActiveColor("#4baaeb")
                .setInActiveColor("#ff666666")
                .setBarBackgroundColor("#ECECEC");

        //设置Bottom
        mBotNavBar.setFirstSelectedPosition(0).initialise();
        //设置默认fragment
        onTabSelected(0);

        mBotNavBar.setTabSelectedListener(this);

        setBottomNavigationItem(mBotNavBar, 15, 25, 15);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(int position) {
        //开启一个Fragment的事物
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //先隐藏掉所有的Fragment,以防多个Fragment同时出现在界面上
        hideFragment(fragmentTransaction);
        switch (position) {
            case 0: {
                if (bottomFragment1 == null) {
                    bottomFragment1 = new BottomFragment1();
                    fragmentTransaction.add(R.id.layFrame, bottomFragment1);
                } else {
                    fragmentTransaction.show(bottomFragment1);
                }
            }
            break;
            case 1: {
                if (bottomFragment2 == null) {
                    bottomFragment2 = new BottomFragment2();
                    fragmentTransaction.add(R.id.layFrame, bottomFragment2);
                } else {
                    fragmentTransaction.remove(bottomFragment2);
                    bottomFragment2 = new BottomFragment2();
                    fragmentTransaction.add(R.id.layFrame, bottomFragment2);
                }

            }
            break;
            case 2: {
                if (bottomFragment3 == null) {
                    bottomFragment3 = new BottomFragment3();
                    fragmentTransaction.add(R.id.layFrame, bottomFragment3);
                } else {
                    fragmentTransaction.remove(bottomFragment3);
                    bottomFragment3 = new BottomFragment3();
                    fragmentTransaction.add(R.id.layFrame, bottomFragment3);
                }
            }
            break;
            case 3: {
                if (bottomFragment4 == null) {
                    bottomFragment4 = new BottomFragment4();
                    fragmentTransaction.add(R.id.layFrame, bottomFragment4);
                } else {
                    fragmentTransaction.show(bottomFragment4);
                }

            }
            break;
            default:
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (bottomFragment1 != null) {
            fragmentTransaction.hide(bottomFragment1);
        }
        if (bottomFragment2 != null) {
            fragmentTransaction.hide(bottomFragment2);
        }
        if (bottomFragment3 != null) {
            fragmentTransaction.hide(bottomFragment3);
        }
        if (bottomFragment4 != null) {
            fragmentTransaction.hide(bottomFragment4);
        }
    }

    /*
     * @param bottomNavigationBar，需要修改的 BottomNavigationBar
     * @param space 图片与文字之间的间距
     * @param imgLen 单位：dp，图片大小，应 <= 36dp
     * @param textSize 单位：dp，文字大小，应 <= 20dp
     *
     *  使用方法：直接调用setBottomNavigationItem(bottomNavigationBar, 6, 26, 10);
     *  代表将bottomNavigationBar的文字大小设置为10dp，图片大小为26dp，二者间间距为6dp
     *
     * */
    private void setBottomNavigationItem(BottomNavigationBar bottomNavigationBar, int space, int imgLen, int textSize){
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            if(field.getName().equals("mTabContainer")){
                try{
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for(int j = 0; j < mTabContainer.getChildCount(); j++){
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(dip2px(12), dip2px(0), dip2px(12), dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0,0,0,dip2px(20-textSize - space/2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(dip2px(imgLen), dip2px(imgLen));
                        params.setMargins(0,0,0,space/2);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
