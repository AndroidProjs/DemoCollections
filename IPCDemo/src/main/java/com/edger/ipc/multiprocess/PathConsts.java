package com.edger.ipc.multiprocess;

import android.os.Environment;

import java.io.File;

public class PathConsts {
    public static final String PATH = Environment.getExternalStorageDirectory().getPath();
    public static final String CACHE_PATH =
            Environment.getExternalStorageDirectory().getPath() + File.separator + "usercache";
}
