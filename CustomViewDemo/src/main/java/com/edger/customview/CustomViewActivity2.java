package com.edger.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.customview.widget.HorizontalScrollViewExt2;
import com.edger.customview.widget.ListViewExt;

import java.util.ArrayList;

public class CustomViewActivity2 extends AppCompatActivity {

    private static final String TAG = "CustomViewActivity2";

    private HorizontalScrollViewExt2 mHorizontalScrollViewEx2;

    int[] colors = {Color.parseColor("#9986b0ed"),
            Color.parseColor("#99b3c7e6"), Color.parseColor("#994072bf"),
            Color.parseColor("#FF8F00")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view2);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = getLayoutInflater();
        mHorizontalScrollViewEx2 = findViewById(R.id.custom_demo_horizontal_scrollview_ex2);
        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.custom_demo_list_view_layout2,
                    mHorizontalScrollViewEx2, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.custom_demo_list_view_title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(colors[i]);
            creatList(layout);
            mHorizontalScrollViewEx2.addView(layout);
        }
    }

    private void creatList(ViewGroup layout) {
        ListViewExt listView = layout.findViewById(R.id.custom_demo_list_view);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.custom_demo_list_view_item, R.id.custom_demo_list_view_item_tv, datas);
        listView.setAdapter(adapter);
        listView.setHorizontalScrollViewExt2(mHorizontalScrollViewEx2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CustomViewActivity2.this, "click item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }
}