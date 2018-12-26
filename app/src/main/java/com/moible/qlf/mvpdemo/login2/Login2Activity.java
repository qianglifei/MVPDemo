package com.moible.qlf.mvpdemo.login2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.customview.LoadingFrame;

public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        final LoadingFrame loadingFrame = new LoadingFrame(this) {
            @Override
            public View onSuccessView() {
                return this;
            }

            @Override
            public int onLoad() {
                return 200;
            }
        };

        loadingFrame.show();
    }
}
