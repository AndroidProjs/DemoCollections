package com.edger.democollections;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.didichuxing.doraemonkit.DoraemonKit;

/**
 * @author edger
 */
public class DemoCollectionsApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // DoraemonKit.install(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // MultiDex.install(this);
    }
}
