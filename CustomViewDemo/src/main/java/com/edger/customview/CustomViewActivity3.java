package com.edger.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity3 extends AppCompatActivity {

    private ListView mListView;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view3);
        initView();
    }

    private void initView() {
        mListView = findViewById(R.id.id_content_view);
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("iPhone " + (i + 1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CustomViewActivity3.this, data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}