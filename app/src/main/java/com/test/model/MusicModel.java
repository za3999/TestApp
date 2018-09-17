package com.test.model;

import com.test.util.MusicManagerWrapper;
import com.test.util.MusicUtil;
import com.test.util.RxUtil;
import com.ting.music.model.Music;
import com.ting.music.model.MusicFile;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

public class MusicModel {

    public static void getRecommendMusic(Observer observer) {
        Observable.create(new ObservableOnSubscribe<List<Music>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Music>> emitter) {
                try {
                    emitter.onNext(MusicManagerWrapper.getInstance().getRecommendDailySongs(-1, -1));
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        }).compose(RxUtil.<List<Music>>applySchedulers()).subscribe(observer);
    }

    public static void getMusicFile(final long musicId, final String quality, final String musicType, Observer observer) {
        Observable.create(new ObservableOnSubscribe<MusicFile>() {
            @Override
            public void subscribe(ObservableEmitter<MusicFile> emitter) {
                try {
                    emitter.onNext(MusicManagerWrapper.getInstance().getMusicFile(musicId, quality, musicType));
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        }).compose(RxUtil.<MusicFile>applySchedulers()).subscribe(observer);
    }

    public static void searchMusic(final String key, Observer observer) {
        Observable.create(new ObservableOnSubscribe<List<Music>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Music>> emitter) {
                try {
                    emitter.onNext(MusicManagerWrapper.getInstance().searchSong(key, MusicUtil.CONSTANT.SEARCH_PAGE, MusicUtil.CONSTANT.SEARCH_LIMIT));
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        }).compose(RxUtil.<List<Music>>applySchedulers()).subscribe(observer);
    }

}
