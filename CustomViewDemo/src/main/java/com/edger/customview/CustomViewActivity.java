package com.edger.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.edger.customview.widget.HorizontalScrollViewExt;

import java.util.ArrayList;

public class CustomViewActivity extends AppCompatActivity {

    private static final String TAG = "CustomViewActivity";

    private HorizontalScrollViewExt mHorizontalScrollViewEx;

    int[] colors = {Color.parseColor("#9986b0ed"),
            Color.parseColor("#99b3c7e6"), Color.parseColor("#994072bf"),
            Color.parseColor("#FF8F00")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = getLayoutInflater();
        mHorizontalScrollViewEx = findViewById(R.id.custom_demo_horizontal_scrollview_ex2);
        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.custom_demo_list_view_layout,
                    mHorizontalScrollViewEx, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.custom_demo_list_view_title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(colors[i]);
            creatList(layout);
            mHorizontalScrollViewEx.addView(layout);
        }
    }

    private void creatList(ViewGroup layout) {
        ListView listView = layout.findViewById(R.id.custom_demo_list_view);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.custom_demo_list_view_item, R.id.custom_demo_list_view_item_tv, datas);
        listView.setAdapter(adapter);
    }
}