package com.test.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.ting.music.SDKEngine;
import com.ting.music.SDKInterface;
import com.ting.music.manager.DataRequestThreadPool;
import com.ting.music.manager.Job;
import com.ting.music.model.BaseObject;
import com.ting.music.model.Music;
import com.ting.music.model.MusicFile;
import com.ting.music.model.PlaylistMusicList;
import com.ting.music.model.SearchResultEx;
import com.ting.music.onlinedata.OnlineManagerEngine;
import com.ting.music.onlinedata.SearchManager;
import com.ting.utils.LogUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MusicManagerWrapper {

    private static final String AUTHORITY = "com.smartisanos.music.aiting";
    private static final boolean AITING_TEST_ENV = false;
    private static final boolean AITING_LOG = false;
    public static final String DAILY_SONG = "261";
    //test key
    private static final String TING_SECRET_KEY_TEST = "da70fad2ae34a7e306f5aaffffd36cb2";
    //Release key
    private static final String TING_SECRET_KEY_RELEASE = "acd126dadc816bb50ff04dba72c05a25";
    private static final String TING_APP_KEY = "CZAndroidClient";

    Context mContext;
    private static volatile MusicManagerWrapper mInstance = null;
    private OnlineManagerEngine mEngine;

    private MusicManagerWrapper(Context context) {
        mContext = context;
        initSDK(context);
    }

    public static MusicManagerWrapper init(Context context) {
        if (mInstance == null) {
            synchronized (MusicManagerWrapper.class) {
                if (mInstance == null) {
                    mInstance = new MusicManagerWrapper(context);
                }
                return mInstance;
            }
        }
        return mInstance;
    }

    public static MusicManagerWrapper getInstance(){
        return mInstance;
    }

    /**
     * 搜索 (歌曲、专辑、艺术家)
     *
     * @param songName
     * @param page
     * @param limit
     * @return  {@link com.ting.music.model.Music}
     */
    public List<Music> searchSong(String songName, int page, int limit) throws Exception {
        SearchResultEx searchResult = mEngine.getSearchManager(mContext).aggregateSearchSync(songName,
                SearchManager.SEARCH_TARGET_ITEM, page, limit, false);
        if (searchResult.isAvailable()) {
            return searchResult.mMusics;
        } else {
            throw new Exception("Error code is:" + searchResult.getErrorCode() + ",description:" + searchResult.getErrorDescription());
        }
    }

    /**
     * 获取推荐的歌曲列表
     *
     * @param page
     * @param limit
     * @return {@link com.ting.music.model.Music}
     */
    public List<Music> getRecommendDailySongs(int page, int limit) throws Exception {
        PlaylistMusicList playListInfo;
        if (page <= 0 || limit <= 0) {
            playListInfo = mEngine.getPlayListManager(mContext).getPlayListInfo(mContext, DAILY_SONG, false);
        } else {
            playListInfo = mEngine.getPlayListManager(mContext).getPlayListInfo(mContext, DAILY_SONG, page, limit, false);
        }
        if (playListInfo.isAvailable()) {
            return playListInfo.getItems();
        } else {
            throw new Exception("Error code is:" + playListInfo.getErrorCode() + ",description:" + playListInfo.getErrorDescription());
        }
    }

    /**
     * 获取歌曲的文件信息
     *
     * @param musicId
     * @param quality   {@link com.ting.music.onlinedata.MusicManager#BITRATE_128K }
     *                  {@link com.ting.music.onlinedata.MusicManager#BITRATE_192K }
     *                  {@link com.ting.music.onlinedata.MusicManager#BITRATE_256K }
     *                  {@link com.ting.music.onlinedata.MusicManager#BITRATE_320K }
     *                  {@link com.ting.music.onlinedata.MusicManager#BITRATE_FLAC }
     * @param musicType {@link com.ting.music.onlinedata.MusicManager#TYPE_LISTEN }
     *                  {@link com.ting.music.onlinedata.MusicManager#TYPE_DOWNLOAD }
     * @return {@link com.ting.music.model.MusicFile}
     */
    public MusicFile getMusicFile(long musicId, String quality, String musicType) throws Exception {
        Music music = mEngine.getMusicManager(mContext).getMusicSync(musicId, quality);
        MusicFile musicFile = mEngine.getMusicManager(mContext).getMusicFileSync(music, musicType, quality);
        if (musicFile.isAvailable()) {
            return musicFile;
        } else {
            throw new Exception("Error code is:" + musicFile.getErrorCode() + ",description:" + musicFile.getErrorDescription());
        }
    }

    private void initSDK(Context context) {
        SDKInterface sdkInterface = new SDKInterface() {

            @Override
            public void onOrdinaryInvalid() {
            }

            @Override
            public void onAccountTokenInvalid() {
            }
        };
        DataRequestThreadPool.submit(new Job() {

            @Override
            protected void run() {
            }
        });
        //TODO for test
        String key = AITING_TEST_ENV ? TING_SECRET_KEY_TEST : TING_SECRET_KEY_RELEASE;
        SDKEngine.getInstance().init(context, TING_APP_KEY, key, MusicUtil.getDeviceId(context), sdkInterface);
        SDKEngine.getInstance().setTest(AITING_TEST_ENV);
        mEngine = OnlineManagerEngine.getInstance(context);
    }


}
