package com.edger.recycleviewdemo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.edger.recycleviewdemo.adapter.RecyclerViewAdapter;
import com.edger.recycleviewdemo.adapter.RecyclerViewAdapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewDemoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_demo_main);
        this.setTitle("RecycleView Demo");

        initData();

        initView();

        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mData);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        // 设置 RecyclerView 的布局管理
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        // 设置增删动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置 RecyclerView 的 Item 间隔
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));

        // 设置 Item 的监听
        mRecyclerViewAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(RecycleViewDemoActivity.this, "Click At " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(RecycleViewDemoActivity.this, "Long Click At " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initData() {
        mData = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mData.add("" + (char) i);
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            mRecyclerViewAdapter.add(1);
        } else if (id == R.id.action_remove) {
            mRecyclerViewAdapter.remove(1);
        } else if (id == R.id.action_list_view) {
            mRecyclerView.setLayoutManager(layoutManager);
        } else if (id == R.id.action_grid_view_vertical) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        } else if (id == R.id.action_grid_view_horizontal) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, false));
        } else if (id == R.id.action_staggered_view) {
            Intent intent = new Intent(this, StaggeredViewActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
