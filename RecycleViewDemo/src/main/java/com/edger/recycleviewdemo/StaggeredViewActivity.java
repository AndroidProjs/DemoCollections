package com.edger.recycleviewdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.edger.recycleviewdemo.adapter.RecyclerViewAdapter.OnItemClickListener;
import com.edger.recycleviewdemo.adapter.StaggeredViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class StaggeredViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;

    private StaggeredViewAdapter mStaggeredViewAdapter;
    private LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_demo_main);
        this.setTitle("RecyclerView Demo");

        initData();

        initView();

        mStaggeredViewAdapter = new StaggeredViewAdapter(this, mData);
        mRecyclerView.setAdapter(mStaggeredViewAdapter);

        // 设置 RecyclerView 的布局管理
        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        // 设置 Item 的监听
        mStaggeredViewAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                mStaggeredViewAdapter.remove(position);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                mStaggeredViewAdapter.remove(position);
            }
        });

    }

    private void initData() {
        mData = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++) {
            mData.add("" + (char) i);
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }
}
