package com.test.common;

import android.view.View;

public class DefaultViewHolder<T> extends BaseViewHolder<T> {

    public DefaultViewHolder(View view) {
        super(view);
    }

    public DefaultViewHolder(View view, boolean itemClickEnable) {
        super(view, itemClickEnable);
    }

    @Override
    public void bindData(T t) {

    }

    @Override
    public void initView(View view) {

    }
}
