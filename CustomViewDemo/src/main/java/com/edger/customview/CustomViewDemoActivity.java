package com.edger.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author edger
 */
public class CustomViewDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EntryAdapter entryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_demo_collections);
        this.setTitle("CustomView Demo");

        initListData();
        initViews();
    }

    private void initListData() {
        List<String> entryList = new LinkedList<>();
        entryList.add("（一）Custom View Demo");
        entryList.add("（二）Custom View Demo2");
        entryList.add("（三）Custom View Demo3");
        entryList.add("（四）Drawing Demo");

        entryAdapter = new EntryAdapter(this, entryList);
    }

    private void initViews() {
        ListView entryListView = findViewById(R.id.custom_view_demo_entry_list);
        entryListView.setAdapter(entryAdapter);
        entryListView.setOnItemClickListener(this);
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(this, CustomViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, CustomViewActivity2.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, CustomViewActivity3.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, DrawingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
