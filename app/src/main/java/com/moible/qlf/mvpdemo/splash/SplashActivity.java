package com.moible.qlf.mvpdemo.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;

import com.moible.bksx.xcb.granor.PermissionListener;
import com.moible.bksx.xcb.granor.PermissionsUtil;
import com.moible.qlf.baseframework.base.BaseActivity;
import com.moible.qlf.mvpdemo.main.MainActivity;
import com.moible.qlf.mvpdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qlf
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ConstraintLayout imageView;
    private String[] strPermissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA //相机
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        PermissionsUtil.requestPermission(mContext, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String... permission) {
                Log.i(TAG, "===permissionGranted: ");
                Intent intent = new Intent(mContext,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void permissionDenied(String... permission) {
                Log.i(TAG, "===permissionGranted: ");
            }
        },strPermissions,true,null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
