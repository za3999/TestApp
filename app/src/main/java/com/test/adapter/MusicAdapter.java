package com.test.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.R;
import com.test.business.DownloadBusiness;
import com.test.util.DownloadUtil;
import com.test.util.MusicUtil;
import com.ting.music.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends BaseAdapter {

    Context mContext;

    List<Music> mMusics = new ArrayList<>();

    public MusicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mMusics.size();
    }

    @Override
    public Object getItem(int position) {
        return mMusics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setMusics(List<Music> musics) {
        this.mMusics.clear();
        mMusics.addAll(musics);
        notifyDataSetChanged();
    }

    public List<Music> getMusics() {
        return new ArrayList<>(mMusics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.music_item, null);
            holder = new ViewHolder();
            holder.initView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindData(mMusics.get(position));
        return convertView;
    }

    private static class ViewHolder {
        View rootView;
        Music mMusic;
        TextView mName;
        TextView mArtist;
        Button mDownload;
        ImageView mBitrate;

        public void initView(View view) {
            rootView = view;
            mName = (TextView) view.findViewById(R.id.name);
            mArtist = (TextView) view.findViewById(R.id.artist);
            mDownload = (Button) view.findViewById(R.id.download);
            mBitrate = (ImageView) view.findViewById(R.id.bitrate_im);
        }

        public void bindData(final Music music) {
            this.mMusic = music;
            mName.setText(mMusic.mTitle);
            mArtist.setText(mMusic.mArtist);
            mBitrate.setVisibility(View.GONE);
            if (MusicUtil.existSqBitrate(music.bitrates)) {
                mBitrate.setVisibility(View.VISIBLE);
                mBitrate.setImageResource(R.mipmap.audio_quality_sq);
            } else if (MusicUtil.existHqBitrate(music.bitrates)) {
                mBitrate.setVisibility(View.VISIBLE);
                mBitrate.setImageResource(R.mipmap.audio_quality_hq);
            }
            mDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DownloadBusiness.download(mMusic);
                }
            });
        }
    }
}
