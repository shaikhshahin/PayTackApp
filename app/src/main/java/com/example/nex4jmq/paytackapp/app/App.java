package com.example.nex4jmq.paytackapp.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.nex4jmq.paytackapp.utility.TypefaceUtil;

public class App extends Application {

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "font/nunito_regular.ttf");
    }
}
