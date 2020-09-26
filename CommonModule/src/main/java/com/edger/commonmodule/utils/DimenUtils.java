package com.edger.commonmodule.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class DimenUtils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
    public static float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
    public static float getZlocationForCamera(float z) {
        return z * Resources.getSystem().getDisplayMetrics().density;
    }
}
