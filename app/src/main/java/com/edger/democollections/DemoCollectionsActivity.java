package com.edger.democollections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.animation.AnimationActivity;
import com.edger.customview.CustomViewDemoActivity;
import com.edger.listviewdemo.ListViewDemoActivity;
import com.edger.materialdemo.MaterialDemoActivity;
import com.edger.networkdemo.NetworkDemoActivity;
import com.edger.notificationdemo.NotificationActivity;
import com.edger.recycleviewdemo.RecycleViewDemoActivity;
import com.edger.servicedemo.ServiceDemoActivity;
import com.edger.threaddemo.ThreadDemoActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author edger
 */
public class DemoCollectionsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView entryListView;
    private EntryAdapter entryAdapter;
    private List<String> entryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_collections);

        initListData();
        initViews();
    }

    private void initListData() {
        entryList = new LinkedList<>();
        entryList.add("一、ListView Demo");
        entryList.add("二、RecyclerView Demo");
        entryList.add("三、Network Demo");
        entryList.add("四、Thread Demo");
        entryList.add("五、Service Demo");
        entryList.add("六、Notification Demo");
        entryList.add("七、Material Demo");
        entryList.add("八、Custom View Demo");
        entryList.add("九、Animation Demo");

        entryAdapter = new EntryAdapter(this, entryList);
    }

    private void initViews() {
        entryListView = findViewById(R.id.app_entry_list);
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
                intent = new Intent(this, ListViewDemoActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, RecycleViewDemoActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, NetworkDemoActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, ThreadDemoActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, ServiceDemoActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, MaterialDemoActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(this, CustomViewDemoActivity.class);
                startActivity(intent);
                break;
            case 8:
                intent = new Intent(this, AnimationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
