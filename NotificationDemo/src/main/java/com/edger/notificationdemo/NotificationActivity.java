package com.edger.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;

public class NotificationActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "NotificationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button sendNoticeBtn = findViewById(R.id.send_notice);
        sendNoticeBtn.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_notice) {
            Log.d(TAG, "onClick: ");
            //**************************** 普通做法
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat
                    // ChannelId 为 "notification_demo", 与 NotificationChannel 一致
                    .Builder(this, "notification_demo")
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_round))
                    .build();

            // Android 8.0 以上需要设置 NotificationChannel，否则通知无法显示
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel;
                // ChannelId 为 "notification_demo", ChannelName 为 "Notification Demo"
                channel = new NotificationChannel("notification_demo",
                        "Notification Demo", NotificationManager.IMPORTANCE_DEFAULT);
                // 是否在桌面icon右上角展示小红点
                channel.enableLights(true);
                // 小红点颜色
                channel.setLightColor(Color.GREEN);
                // 是否在久按桌面图标时显示此渠道的通知
                channel.setShowBadge(true);
                // channel.setSound(null, null);
                // channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{0, 1000, 1000, 1000});
                manager.createNotificationChannel(channel);
            }
            manager.notify(1, notification);

            //**************************** 使用Android Util 工具类
            Intent intent = new Intent(this, NotificatoinActivity2.class);
            final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            NotificationUtils.notify(2,
                    new NotificationUtils.ChannelConfig("notification_demo2",
                            "Notification Demo2", NotificationUtils.IMPORTANCE_DEFAULT),
                    new Utils.Func1<Void, NotificationCompat.Builder>() {
                        @Override
                        public Void call(NotificationCompat.Builder param) {
                            param.setContentTitle("Go to Service Demo")
                                    // .setContentText("Click to jump.............................." +
                                    //         "..................................................." +
                                    //         "...................................................")
                                    .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                            "Click to jump.............................." +
                                            "..................................................." +
                                            "..................................................."))
                                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                            BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)))
                                    .setContentIntent(pendingIntent)
                                    .setAutoCancel(true)
                                    .setPriority(NotificationCompat.PRIORITY_MAX)
                                    .setWhen(System.currentTimeMillis())
                                    // .setDefaults(NotificationCompat.DEFAULT_ALL)
                                    .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Lumina.mp3")))
                                    .setVibrate(new long[]{0, 1000, 1000, 1000})
                                    .setLights(Color.GREEN, 1000, 100)
                                    .setSmallIcon(R.drawable.ic_launcher_round)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                            R.drawable.ic_launcher_round));
                            return null;
                        }
                    });
        }
    }
}
