package com.edger.listviewdemo;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ListViewDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private List<Animal> animalListData;
    private Context mContext;
    private ListView animalListView;
    private View animLlistViewHeader;
    private Button btnAddItem;
    private Button btnAddItemAtFifth;
    private Button btnRemoveItem;
    private Button btnRemoveItemAtSixth;
    private Button btnClear;
    private AnimalAdapter animalAdapter;
    private int animalSum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_demo_main);
        this.setTitle("ListView Demo");

        mContext = ListViewDemoActivity.this;

        initView();

        animalListData = new LinkedList<Animal>();
        animalListData.add(new Animal(++animalSum, "Cat ", "Are you a cat?", R.drawable.list_view_demo_cat));
        animalListData.add(new Animal(++animalSum, "Cow ", "Are you a cow?", R.drawable.list_view_demo_cow));
        animalListData.add(new Animal(++animalSum, "fish", "Are you a fish?", R.drawable.list_view_demo_fish));
        animalListData.add(new Animal(++animalSum, "Dog ", "Are you a dog?", R.drawable.list_view_demo_dog));
        animalListData.add(new Animal(++animalSum, "duck", "Are you a duck?", R.drawable.list_view_demo_duck));

        animalAdapter = new AnimalAdapter(animalListData, mContext);
        animalListView.addHeaderView(animLlistViewHeader);
        animalListView.setAdapter(animalAdapter);
        animalListView.setOnItemClickListener(this);
    }

    private void initView() {
        btnAddItem = findViewById(R.id.add_item);
        btnAddItem.setOnClickListener(this);
        btnAddItemAtFifth = findViewById(R.id.add_item_at_fifth);
        btnAddItemAtFifth.setOnClickListener(this);
        btnRemoveItem = findViewById(R.id.remove_item);
        btnRemoveItem.setOnClickListener(this);
        btnRemoveItemAtSixth = findViewById(R.id.remove_item_at_sixth);
        btnRemoveItemAtSixth.setOnClickListener(this);
        btnClear = findViewById(R.id.clear_all_item);
        btnClear.setOnClickListener(this);
        animalListView = findViewById(R.id.list_view_animals);

        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        animLlistViewHeader = layoutInflater.inflate(R.layout.list_view_demo_header, null, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext, "You had clicked " + position + "th item", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.add_item) {
            animalAdapter.add(new Animal(++animalSum, "Dog ", "Are you a dog?", R.drawable.list_view_demo_dog));
        } else if (id == R.id.add_item_at_fifth) {
            boolean success = animalAdapter.add(5, new Animal(++animalSum, "Cat ", "Are you a cat?", R.drawable.list_view_demo_cat));
            if (!success) {
                animalSum--;
            }
        } else if (id == R.id.remove_item) {
            animalAdapter.remove(new Animal(animalSum, "Dog ", "Are you a dog?", R.drawable.list_view_demo_dog));
        } else if (id == R.id.remove_item_at_sixth) {
            animalAdapter.remove(6);
        } else if (id == R.id.clear_all_item) {
            animalAdapter.clear();
            animalSum = 0;
        }
    }
}
