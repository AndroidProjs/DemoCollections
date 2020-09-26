package com.edger.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NotificatoinActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificatoin2);

        // 取消 NotificationActivity 启动 ServiceDemoActivity 的通知
        // NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // manager.cancel(2);
    }
}
