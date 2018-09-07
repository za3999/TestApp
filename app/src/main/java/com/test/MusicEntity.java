package com.test;

import android.os.Parcel;
import android.os.Parcelable;


public class MusicEntity implements Parcelable {

    private String url;
    private String singer;
    private String musicTitle;
    private String album;

    protected MusicEntity(Parcel in) {
        url = in.readString();
        singer = in.readString();
        musicTitle = in.readString();
        album = in.readString();
    }

    public MusicEntity() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(singer);
        dest.writeString(musicTitle);
        dest.writeString(album);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MusicEntity> CREATOR = new Creator<MusicEntity>() {
        @Override
        public MusicEntity createFromParcel(Parcel in) {
            return new MusicEntity(in);
        }

        @Override
        public MusicEntity[] newArray(int size) {
            return new MusicEntity[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
