package com.example.q1833;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // По истечении времени, запускаем главный активити, а Splash Screen закрываем
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                   // overridePendingTransition(R.anim.transition_splash_start, R.anim.transition_splash_end);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
}