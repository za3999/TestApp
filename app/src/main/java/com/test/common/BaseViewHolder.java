package com.test.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected int position;
    protected BaseCallBack.CallBack3<Integer, View, T> itemClick;
    protected T itemData;

    public BaseViewHolder(View view) {
        super(view);
        initView(view);
        itemView.setOnClickListener(v -> onItemClick(position, itemView, itemData));
    }

    public BaseViewHolder(View view, boolean itemClickEnable) {
        super(view);
        initView(view);
        if (itemClickEnable) {
            itemView.setOnClickListener(v -> onItemClick(position, itemView, itemData));
        }
    }

    public Context getContext() {
        return itemView.getContext();
    }

    protected void onBindData(T data, BaseCallBack.CallBack3<Integer, View, T> itemClick, int position) {
        this.itemClick = itemClick;
        this.position = position;
        this.itemData = data;
        bindData(data);
    }

    public T getItemData() {
        return itemData;
    }

    public abstract void bindData(T t);

    public abstract void initView(View itemView);

    public void onItemClick(int position, View v, T t) {
        itemClick.onCallBack(position, v, itemData);
    }
}
