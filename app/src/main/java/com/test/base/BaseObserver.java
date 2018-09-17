package com.test.base;

import android.widget.Toast;

import com.test.MusicApplication;
import com.test.util.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        String message = "error:" + e.getMessage();
        Toast.makeText(MusicApplication.getApplication(),message,Toast.LENGTH_LONG).show();
        LogUtils.d(message);
    }

    @Override
    public void onComplete() {

    }
}
