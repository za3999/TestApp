package com.test;

import android.app.Application;

import com.test.util.MusicManagerWrapper;

public class MusicApplication extends Application {

    private static MusicApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MusicManagerWrapper.init(this);
    }

    public static MusicApplication getApplication() {
        return instance;
    }
}
