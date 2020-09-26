package com.edger.commonmodule.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用 ViewHolder
 */
public class CommonViewHolder {

    // 所有控件集合
    private SparseArray<View> mViews;
    // 记录位置
    private int mPosition;
    // 可复用 View
    private View mConvertView;

    /**
     * ViewHolder 构造函数
     *
     * @param context   上下文对象
     * @param parent    父类容器
     * @param layoutId  布局 ID
     * @param position  子项的位置
     */
    public CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        this.mPosition = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取 ViewHolder 对象
     *
     * @param context       上下文对象
     * @param convertView   复用的 View
     * @param parent        父类容器
     * @param layoutId      布局 ID
     * @param position      子项的位置
     * @return
     */
    public static CommonViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        // 若为空，则新建 ViewHolder
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        } else {
            // 否则，返回已有 ViewHolder
            CommonViewHolder viewHolder = (CommonViewHolder) convertView.getTag();
            // 记录更新条目的位置
            viewHolder.mPosition = position;
            return viewHolder;
        }
    }

    /**
     * @return  返回复用的 View
     */
    public View getmConvertView() {
        return mConvertView;
    }

    /**
     * @return  返回当前条目的位置
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * 通过 viewId 获取控件
     *
     * @param viewId    控件的 ID
     * @param <T>       View 的子类
     * @return          返回view
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }

    /**
     * @param viewId    TextView 的 ID
     * @param text      文本
     * @return
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * @param viewId    ImageView 的 ID
     * @param resId     图片资源的 ID
     * @return
     */
    public CommonViewHolder setImage(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

}
