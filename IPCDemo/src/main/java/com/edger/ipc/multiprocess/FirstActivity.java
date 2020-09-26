package com.edger.ipc.multiprocess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.edger.ipc.R;
import com.edger.ipc.model.UserSerializable;
import com.edger.ipc.manager.UserManager;
import com.edger.ipc.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        UserManager.userId = 2;
        Log.d(TAG, "onCreate: user id " + UserManager.userId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        persistToFile();
    }

    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserSerializable user = new UserSerializable(0, "jake", true);

                File dir = new File(PathConsts.PATH);
                if (!dir.exists()) {
                    Log.d(TAG, "dir exists? " + dir.exists());
                    dir.mkdirs();
                }

                File cacheFile = new File(PathConsts.CACHE_PATH);

                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(new FileOutputStream(cacheFile));
                    out.writeObject(user);
                    Log.d(TAG, "persist user : " + user.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(out);
                }

            }
        }).start();
    }
}