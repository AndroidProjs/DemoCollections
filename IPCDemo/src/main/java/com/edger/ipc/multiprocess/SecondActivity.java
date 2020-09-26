package com.edger.ipc.multiprocess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.edger.ipc.R;
import com.edger.ipc.manager.UserManager;
import com.edger.ipc.model.UserSerializable;
import com.edger.ipc.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d(TAG, "onCreate: user id " + UserManager.userId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recoverFromFile();
    }

    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream in = null;
                try {
                    File cacheFile = new File(PathConsts.CACHE_PATH);
                    in = new ObjectInputStream(new FileInputStream(cacheFile));
                    UserSerializable newUser = (UserSerializable) in.readObject();
                    Log.d(TAG, "recover user : " + newUser.toString());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(in);
                }
            }
        }).start();
    }
}