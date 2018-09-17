package com.test.business;

import android.widget.Toast;

import com.test.MusicApplication;
import com.test.base.BaseObserver;
import com.test.model.MusicModel;
import com.test.util.DownloadUtil;
import com.test.util.LogUtils;
import com.ting.music.model.Music;
import com.ting.music.model.MusicFile;
import com.ting.music.onlinedata.MusicManager;

public class DownloadBusiness {

    public static void download(final Music music) {
        if (DownloadUtil.isMusicExist(music.mTitle, music.mArtist)) {
            Toast.makeText(MusicApplication.getApplication(), "歌曲已下载", Toast.LENGTH_LONG).show();
            return;
        }

        MusicModel.getMusicFile(Long.parseLong(music.mId), music.bitrates.get(0),
                MusicManager.TYPE_DOWNLOAD, new BaseObserver<MusicFile>() {
                    @Override
                    public void onNext(MusicFile musicFile) {
                        LogUtils.d(musicFile.toString());
                        long downloadId = DownloadUtil.download(musicFile.mFileLink, musicFile.mTitle, music.mArtist);
                        if (downloadId > 0) {
                            Toast.makeText(MusicApplication.getApplication(), "开始下载", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
