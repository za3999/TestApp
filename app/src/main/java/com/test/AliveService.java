package com.test;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.test.util.LogUtils;

public class AliveService extends Service {

    private static final int notifyId = 1111;

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(notifyId,new Notification());
        LogUtils.d("startForeground");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
