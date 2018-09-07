// IAudioAIdl.aidl
package com.test;
import com.test.MusicEntity;
interface IAudioAIdl {
    void play();
    void skipToNext();
    void skipToPrevious();
    void pause();
    void setPlayeData(in List<MusicEntity> musicList);
}
