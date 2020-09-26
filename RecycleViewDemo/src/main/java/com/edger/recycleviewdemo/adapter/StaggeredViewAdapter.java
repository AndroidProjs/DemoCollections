package com.edger.recycleviewdemo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class StaggeredViewAdapter extends RecyclerViewAdapter {

    private List<Integer> mHeight;

    public StaggeredViewAdapter(Context context, List<String> data) {
        super(context, data);
        initHeight();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        LayoutParams layoutParams = (LayoutParams) viewHolder.itemView.getLayoutParams();
        layoutParams.height = mHeight.get(position);
        viewHolder.itemView.setLayoutParams(layoutParams);
    }

    private void initHeight() {
        mHeight = new ArrayList<Integer>();
        for (int i = 0; i < data.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

}
