package com.test.business;

import android.widget.Toast;

import com.test.MusicApplication;
import com.test.base.BaseObserver;
import com.test.model.MusicModel;
import com.test.util.DownloadUtil;
import com.test.util.LogUtils;
import com.test.util.MusicUtil;
import com.ting.music.model.Music;
import com.ting.music.model.MusicFile;
import com.ting.music.onlinedata.MusicManager;

public class DownloadBusiness {

    public static void download(final Music music) {
        if (DownloadUtil.isMusicExist(music.mTitle, music.mArtist)) {
            Toast.makeText(MusicApplication.getApplication(), "歌曲已下载", Toast.LENGTH_LONG).show();
            return;
        }
        download(music, MusicUtil.getBitrate(music.bitrates, MusicUtil.CONSTANT.MAX_BITRATE_LEVEL), false);
    }

    private static void download(final Music music, final String bitrate, final boolean showBitrate) {
        MusicModel.getMusicFile(Long.parseLong(music.mId), bitrate, MusicManager.TYPE_DOWNLOAD, new BaseObserver<MusicFile>() {
            @Override
            public void onNext(MusicFile musicFile) {
                LogUtils.d(musicFile.toString());
                String fileName = showBitrate ? DownloadUtil.getFileName(music.mTitle, music.mArtist, bitrate)
                        : DownloadUtil.getFileName(music.mTitle, music.mArtist);
                long downloadId = DownloadUtil.download(musicFile.mFileLink, fileName);
                if (downloadId > 0) {
                    Toast.makeText(MusicApplication.getApplication(), "开始下载", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
