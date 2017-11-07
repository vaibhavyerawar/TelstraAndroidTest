package com.TelstraAndroidTest.view;

import android.os.Bundle;
import android.os.Handler;

import com.TelstraAndroidTest.R;

public class SplashActivity extends BaseActivity {

    private final long WAITING_PERIOD = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        mContext = this;
        manageSplashScreen();
    }

    private void manageSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewActivity(FactListActivity.class);
            }
        }, WAITING_PERIOD);
    }
}
