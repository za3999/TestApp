package com.test;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.test.adapter.MusicAdapter;
import com.test.base.BaseObserver;
import com.test.model.MusicModel;
import com.test.util.MusicUtil;
import com.test.util.PlayerManager;
import com.ting.music.model.Music;

import java.util.List;

public class MainActivity extends Activity {

    private ListView mList;
    private MusicAdapter musicAdapter;
    private EditText mSearchEt;
    private View playBar;
    private TextView musicTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onBackPressed() {
        if (TextUtils.isEmpty(mSearchEt.getText())) {
            super.onBackPressed();
        } else {
            mSearchEt.setText("");
        }
    }

    public void search(View view) {
        if (!TextUtils.isEmpty(mSearchEt.getText())) {
            MusicUtil.hideSoftInput(view);
            MusicModel.searchMusic(mSearchEt.getText().toString(), new BaseObserver<List<Music>>() {

                @Override
                public void onNext(List<Music> music) {
                    musicAdapter.setMusics(music);
                }
            });
        }
    }

    public void toogle(View view) {
        PlayerManager.getInstance().toogle();
    }

    private void initView() {
        mList = (ListView) findViewById(R.id.list_view);
        mSearchEt = (EditText) findViewById(R.id.search_et);
        playBar = findViewById(R.id.play_bar_layout);
        musicTitle = (TextView) findViewById(R.id.music_tv);
        musicAdapter = new MusicAdapter(this);
        mList.setAdapter(musicAdapter);
        showRecommendMusic();
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    showRecommendMusic();
                }
            }
        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayerManager.getInstance().start((Music) musicAdapter.getItem(position));
                refPlayView();
            }
        });
        PlayerManager.getInstance().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                PlayerManager.getInstance().release();
                refPlayView();
            }
        });
        refPlayView();
    }


    private void refPlayView() {
        Music music = PlayerManager.getInstance().getMusic();
        musicTitle.setText(music == null ? "" : music.mTitle);
        playBar.setVisibility(music == null ? View.GONE : View.VISIBLE);
    }

    private void showRecommendMusic() {
        MusicModel.getRecommendMusic(new BaseObserver<List<Music>>() {

            @Override
            public void onNext(List<Music> music) {
                musicAdapter.setMusics(music);
            }
        });
    }
}
