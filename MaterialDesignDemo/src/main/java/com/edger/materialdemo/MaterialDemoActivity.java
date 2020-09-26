package com.edger.materialdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MaterialDemoActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_demo);
        Toolbar toolbar = findViewById(R.id.material_demo_toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.material_demo_drawer_layout);
        NavigationView navigationView = findViewById(R.id.material_demo_navigation_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.material_demo_drawer_nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return false;
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.material_demo_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MaterialDemoActivity.this, "FAB clicked", Toast.LENGTH_SHORT)
                // .show();
                Snackbar.make(v, "Data Deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MaterialDemoActivity.this, "Data restored",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.material_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.material_demo_toolbar_backup) {
            Toast.makeText(this, "You clicked backup.", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.material_demo_toolbar_delete) {
            Toast.makeText(this, "You clicked Delete.", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.material_demo_toolbar_setting) {
            Toast.makeText(this, "You clicked Setting.", Toast.LENGTH_SHORT).show();
        } else if (itemId == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}