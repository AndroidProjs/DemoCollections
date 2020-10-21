package com.edger.customview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

public class ListViewExt extends ListView {

    private static final String TAG = "ListViewExt";

    /**
     * 分别记录上次滑动的坐标
     */
    private int mLastX = 0;
    private int mLastY = 0;

    private HorizontalScrollViewExt2 mHorizontalScrollViewExt2;

    public ListViewExt(Context context) {
        super(context);
    }

    public ListViewExt(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListViewExt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHorizontalScrollViewExt2(HorizontalScrollViewExt2 horizontalScrollViewExt2) {
        mHorizontalScrollViewExt2 = horizontalScrollViewExt2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHorizontalScrollViewExt2.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "dx:" + deltaX + " dy:" + deltaY);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mHorizontalScrollViewExt2.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
