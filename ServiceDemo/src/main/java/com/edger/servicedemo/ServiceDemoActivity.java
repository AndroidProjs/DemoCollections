package com.edger.servicedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ServiceDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ServiceDemoActivity";

    private MyService.MyServiceBinder myServiceBinder;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myServiceBinder = (MyService.MyServiceBinder) service;
            myServiceBinder.startDownload();
            myServiceBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection downloadConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        Button startService = findViewById(R.id.start_service);
        Button stopService = findViewById(R.id.stop_service);
        Button bindService = findViewById(R.id.bind_service);
        Button unbindService = findViewById(R.id.unbind_service);
        Button startIntentService = findViewById(R.id.start_intent_service);
        Button startDownload = findViewById(R.id.start_download);
        Button pauseDownload = findViewById(R.id.pause_download);
        Button cancelDownload = findViewById(R.id.cancel_download);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
        startDownload.setOnClickListener(this);
        pauseDownload.setOnClickListener(this);
        cancelDownload.setOnClickListener(this);

        Intent intent = new Intent(this, DownloadService.class);
        // 启动服务
        startService(intent);
        // 绑定服务
        bindService(intent, downloadConnection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(ServiceDemoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ServiceDemoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(downloadConnection);
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝权限将无法使用应用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }
        int id = v.getId();
        if (id == R.id.start_service) {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        } else if (id == R.id.stop_service) {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        } else if (id == R.id.bind_service) {
            Intent bindIntent = new Intent(this, MyService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        } else if (id == R.id.unbind_service) {
            unbindService(connection);
        } else if (id == R.id.start_intent_service) {
            Log.d(TAG, "Thread id is " + Thread.currentThread().getId());
            Intent startIntentService = new Intent(this, MyIntentService.class);
            startService(startIntentService);
        } else if (id == R.id.start_download) {
            String url = "https://az619519.vo.msecnd.net/files/PlayaRoja_EN-US9542724846_1366x768.jpg";
            downloadBinder.startDownload(url);
        } else if (id == R.id.pause_download) {
            downloadBinder.pauseDownload();
        } else if (id == R.id.cancel_download) {
            downloadBinder.cancelDownload();
        }
    }
}
