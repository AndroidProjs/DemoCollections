package com.edger.ipc;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.edger.ipc.utils.ProcessUtils;

public class IPCApplication extends Application {
    private static final String TAG = "IPCApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = ProcessUtils.getProcessName(getApplicationContext(), Process.myPid());
        Log.d(TAG, "Application has started, process name : " + processName);
    }
}
