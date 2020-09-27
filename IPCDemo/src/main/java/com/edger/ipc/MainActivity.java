package com.edger.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.ipc.binder.BookManagerActivity;
import com.edger.ipc.binderpool.BinderPoolActivity;
import com.edger.ipc.messenger.MessengerActivity;
import com.edger.ipc.multiprocess.FirstActivity;
import com.edger.ipc.multiprocess.SecondActivity;
import com.edger.ipc.multiprocess.ThirdActivity;
import com.edger.ipc.provider.BookProviderActivity;
import com.edger.ipc.socket.TcpClientActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startFirstActivity = findViewById(R.id.btn_start_first_activity);
        startFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        Button startSecondActivity = findViewById(R.id.btn_start_second_activity);
        startSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Button startThirdActivity = findViewById(R.id.btn_start_third_activity);
        startThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        Button startMessengerActivity = findViewById(R.id.btn_start_messenger_activity);
        startMessengerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessengerActivity.class);
                startActivity(intent);
            }
        });

        Button startBookManagerActivity = findViewById(R.id.btn_start_book_manager_activity);
        startBookManagerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookManagerActivity.class);
                startActivity(intent);
            }
        });

        Button startBookProviderActivity = findViewById(R.id.btn_start_book_provider_activity);
        startBookProviderActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookProviderActivity.class);
                startActivity(intent);
            }
        });

        Button startTcpClientActivity = findViewById(R.id.btn_start_tcp_client_activity);
        startTcpClientActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TcpClientActivity.class);
                startActivity(intent);
            }
        });

        Button startBinderPoolActivity = findViewById(R.id.btn_start_binder_pool_activity);
        startBinderPoolActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BinderPoolActivity.class);
                startActivity(intent);
            }
        });
    }
}