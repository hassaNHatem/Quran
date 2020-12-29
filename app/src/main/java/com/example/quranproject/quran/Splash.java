package com.example.quranproject.quran;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.quranproject.Base.BaseActivity;
import com.example.quranproject.R;

public class Splash extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(activity,Login.class));
                        finish();
                    }
                },2000);
    }
}