package com.test.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.test.MusicApplication;

public class MusicUtil {

    public static class CONSTANT {
        public static final int SEARCH_PAGE = 1;
        public static final int SEARCH_LIMIT = 20;
    }

    public static String getDeviceId(Context context) {
        String imei = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            imei = tm.getDeviceId();
        }
        return imei;
    }

    public static void hideSoftInput(View view){
        InputMethodManager imm = (InputMethodManager) MusicApplication.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
