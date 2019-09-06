package com.test.common;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> result = new ArrayList();
    protected BaseCallBack.CallBack3<Integer, View, T> mItemClickListener = (position, v, item) -> onItemClick(position, v, item);
    protected BaseCallBack.CallBack3<Integer, View, T> mItemLongClickListener = (position, v, item) -> onItemLongClick(position, v, item);

    public void setItemLongClickListener(BaseCallBack.CallBack3<Integer, View, T> itemClickListener) {
        this.mItemLongClickListener = itemClickListener;
    }

    public void setItemClickListener(BaseCallBack.CallBack3<Integer, View, T> itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    @CallSuper
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindData(result.get(position), mItemClickListener, position);
        if (mItemLongClickListener != null) {
            holder.setItemLongClick(mItemLongClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (result.get(position) instanceof MultiItem) {
            return ((MultiItem) result.get(position)).getItemType();
        }
        return 0;
    }

    public T getItem(int position) {
        if (position < result.size())
            return result.get(position);
        else {
            return null;
        }
    }

    public void setData(List<T> list) {
        result.clear();
        if (list != null && !list.isEmpty()) {
            result.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return result;
    }

    public View createView(int layoutId, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public void onItemClick(int position, View v, T item) {

    }

    public void onItemLongClick(int position, View v, T item) {

    }
}
