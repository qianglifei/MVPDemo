package com.moible.qlf.mvpdemo.login2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.moible.qlf.mvpdemo.R;
import com.moible.qlf.mvpdemo.login.customview.StateLayout;

public class Login2Activity extends AppCompatActivity {
    private StateLayout mStateLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout lin = new LinearLayout(this);

        mStateLayout = new StateLayout(this);
        lin.addView(mStateLayout);

        //添加一个加载成功的view
        mStateLayout.bindSuccessView(getsuccessView());

        setContentView(lin);


        //首先展示正在加载的view
        mStateLayout.showLoadingView();
    }

    private View getsuccessView() {
        View successView = View.inflate(this, R.layout.activity_login3, null);
        return successView;
    }
}
