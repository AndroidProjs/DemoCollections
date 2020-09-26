package com.edger.commonmodule.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> list;
    private int layoutId;

    public CommonAdapter(Context context, List<T> list, int layoutId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取 ViewHolder
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(context, convertView, parent, layoutId, position);

        // 设置控件内容
        setViewContent(viewHolder, (T)getItem(position));

        // 返回复用的 View
        return viewHolder.getmConvertView();
    }

    /**
     * 提供抽象方法，设置控件内容
     *
     * @param viewHolder    ViewHolder
     * @param t             数据集
     */
    public abstract void setViewContent(CommonViewHolder viewHolder, T t);

    public void add(T data) {
        if (list == null) {
            list = new LinkedList<T>();
        }
        list.add(data);
        notifyDataSetChanged();
    }

    public boolean add(int position, T data) {
        if (list == null) {
            list = new LinkedList<T>();
        }
        if (list.size() < position) {
            return false;
        }
        list.add(position, data);
        notifyDataSetChanged();
        return true;
    }

    public void remove(T animal) {
        if (list == null) {
            return;
        }
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(animal)) {
                iterator.remove();
            }
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (list == null) {
            return;
        }
        if (list.size() < position) {
            return;
        }
        list.remove(position - 1);
        notifyDataSetChanged();
    }

    public void clear() {
        if (list == null) {
            return;
        }
        list.clear();
        notifyDataSetChanged();
    }

}
