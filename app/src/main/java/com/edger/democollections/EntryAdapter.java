package com.edger.democollections;

import android.content.Context;

import com.edger.commonmodule.utils.CommonAdapter;
import com.edger.commonmodule.utils.CommonViewHolder;

import java.util.List;

public class EntryAdapter extends CommonAdapter<String> {
    public EntryAdapter(Context context, List list) {
        super(context, list, android.R.layout.simple_expandable_list_item_1);
    }

    /**
     * 提供抽象方法，设置控件内容
     *
     * @param viewHolder ViewHolder
     * @param str        数据集
     */
    @Override
    public void setViewContent(CommonViewHolder viewHolder, String str) {
        viewHolder.setText(android.R.id.text1, str);
    }
}
