package com.edger.networkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.networkdemo.http.HttpURLConnectionActivity;
import com.edger.networkdemo.http.OkHttpActivity;
import com.edger.networkdemo.json.JSONParseActivity;
import com.edger.networkdemo.retrofit.RetrofitActivity;
import com.edger.networkdemo.webview.WebViewActivity;
import com.edger.networkdemo.xml.XmlParserActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author edger
 */
public class NetworkDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EntryAdapter entryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_demo_collections);
        this.setTitle("Network Demo");

        initListData();
        initViews();
    }

    private void initListData() {
        List<String> entryList = new LinkedList<>();
        entryList.add("（一）WebView Demo");
        entryList.add("（二）HttpURLConnection Demo");
        entryList.add("（三）OkHttp Demo");
        entryList.add("（四）XmlParser Demo");
        entryList.add("（五）JSON Parse Demo");
        entryList.add("（六）Retrofit Demo");

        entryAdapter = new EntryAdapter(this, entryList);
    }

    private void initViews() {
        ListView entryListView = findViewById(R.id.network_demo_entry_list);
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
                intent = new Intent(this, WebViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, HttpURLConnectionActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, OkHttpActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, XmlParserActivity.class);
                startActivity(intent);
            case 4:
                intent = new Intent(this, JSONParseActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, RetrofitActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
