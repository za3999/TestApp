package com.test.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.test.MusicApplication;
import com.ting.music.onlinedata.MusicManager;

import java.util.ArrayList;

public class MusicUtil {

    public static class CONSTANT {
        public static final int SEARCH_PAGE = 1;
        public static final int SEARCH_LIMIT = 20;
        public static final int MAX_BITRATE_LEVEL = 4;
        public static String[] BITRATE_LEVELS = new String[]{MusicManager.BITRATE_128K, MusicManager.BITRATE_192K,
                MusicManager.BITRATE_256K, MusicManager.BITRATE_320K, MusicManager.BITRATE_FLAC};

    }

    public static String getDeviceId(Context context) {
        String imei = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            imei = tm.getDeviceId();
        }
        return imei;
    }

    public static void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) MusicApplication.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 根据level获取音频质量
     *
     * @param bitrates
     * @param level
     * @return
     */
    public static String getBitrate(ArrayList<String> bitrates, int level) {

        if (bitrates == null || bitrates.size() <= 0) {
            return "";
        }

        String bitrate = bitrates.get(0);
        level = level > CONSTANT.MAX_BITRATE_LEVEL ? CONSTANT.MAX_BITRATE_LEVEL : level;
        level = level < 0 ? 0 : level;
        for (int i = level; i >= 0; i--) {
            if (bitrates.contains(CONSTANT.BITRATE_LEVELS[i])) {
                bitrate = CONSTANT.BITRATE_LEVELS[i];
                break;
            }
        }
        return bitrate;
    }

    public static boolean existHqBitrate(ArrayList<String> bitrates) {
        return !TextUtils.isEmpty(getBitrate(bitrates, CONSTANT.MAX_BITRATE_LEVEL - 1))
                || !TextUtils.isEmpty(getBitrate(bitrates, CONSTANT.MAX_BITRATE_LEVEL - 2))
                || !TextUtils.isEmpty(getBitrate(bitrates, CONSTANT.MAX_BITRATE_LEVEL - 3));
    }

    public static boolean existSqBitrate(ArrayList<String> bitrates) {
        return !TextUtils.isEmpty(getBitrate(bitrates, CONSTANT.MAX_BITRATE_LEVEL));
    }

}
