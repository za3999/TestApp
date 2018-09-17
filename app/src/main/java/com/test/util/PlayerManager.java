package com.test.util;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.test.base.BaseObserver;
import com.test.model.MusicModel;
import com.ting.music.model.Music;
import com.ting.music.model.MusicFile;
import com.ting.music.onlinedata.MusicManager;

import java.io.IOException;

public class PlayerManager {

    private final MediaPlayer player = new MediaPlayer();

    private static PlayerManager mInstance;

    private Music mMusic;

    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    public static PlayerManager getInstance() {
        if (mInstance == null) {
            synchronized (MusicManagerWrapper.class) {
                if (mInstance == null) {
                    mInstance = new PlayerManager();
                }
                return mInstance;
            }
        }
        return mInstance;
    }

    public Music getMusic() {
        return mMusic;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void start(Music music) {
        if (music == null) {
            return;
        }

        this.mMusic = music;
        if (DownloadUtil.isMusicExist(music.mTitle, music.mArtist)) {
            PlayerManager.getInstance().start(music.mTitle, DownloadUtil.getMusicPath(music.mTitle, music.mArtist));
        } else {
            MusicModel.getMusicFile(Long.parseLong(music.mId), music.bitrates.get(music.bitrates.size() - 1),
                    MusicManager.TYPE_LISTEN, new BaseObserver<MusicFile>() {
                        @Override
                        public void onNext(MusicFile musicFile) {
                            PlayerManager.getInstance().start(musicFile.mTitle, musicFile.mFileLink);
                        }
                    });
        }
    }

    private void start(String title, String path) {
        LogUtils.d("start title:" + title + ",path:" + path);
        try {
            player.reset();
            player.setDataSource(path);
            player.prepareAsync();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                }
            });
            player.setOnCompletionListener(mOnCompletionListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toogle() {
        if (player.isPlaying()) {
            pasue();
        } else {
            start();
        }
    }

    public void release() {
        mMusic = null;
    }

    private void pasue() {
        player.pause();
    }

    private void start() {
        player.start();
    }

}
