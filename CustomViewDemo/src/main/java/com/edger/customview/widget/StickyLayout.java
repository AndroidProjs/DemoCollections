package com.edger.customview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import com.edger.customview.R;

public class StickyLayout extends LinearLayout {

    private static final String TAG = "StickyLayout";

    private int mLastX;
    private int mLastY;
    private int mTouchSlop;

    boolean isHeaderViewHidden = false;
    boolean mDragging = false;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private View mHeaderView;
    private int mHeaderViewHeight;

    private View mStickyView;
    /**
     * 内容 View
     */
    private View mContentView;
    /**
     * 内容 View 高度
     */
    private int mContentViewHeight;
    /**
     * 内容 View 可见高度
     */
    private int mContentViewVisibleHeight;

    public StickyLayout(Context context) {
        super(context);
    }

    public StickyLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    /**
     * 布局加载完成
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = findViewById(R.id.id_header_view);
        mStickyView = findViewById(R.id.id_sticky_view);
        mContentView = findViewById(R.id.id_content_view);
    }

    /**
     * 计算控件高度
     *
     * @param widthMeasureSpec  屏幕宽度
     * @param heightMeasureSpec 屏幕高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = mContentView.getLayoutParams();
        params.height = getMeasuredHeight() - mStickyView.getMeasuredHeight();
        mContentViewVisibleHeight = getMeasuredHeight();
        Log.d(TAG, "onMeasure: mContentViewVisibleHeight " + mContentViewVisibleHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentViewHeight = mContentView.getMeasuredHeight();
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        Log.d(TAG, "onSizeChanged: mContentViewHeight " + mContentViewHeight);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVelocityTracker.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            /*
             * 控制权交换逻辑
             * 1.头部 view 没有隐藏 -> StickyLayout 拦截事件
             * 2.头部 view 隐藏且子 view 滚动到最顶部再往下滑动 -> StickyLayout 拦截事件
             */
            int deltaY = y - mLastY;
            ListView listView = (ListView) mContentView;
            View itemView = listView.getChildAt(listView.getFirstVisiblePosition());
            boolean isListViewScrolledToTopAndIsHeaderViewHidden =
                    itemView != null && itemView.getTop() == 0 && isHeaderViewHidden && deltaY > 0;
            Log.d(TAG, "onInterceptHoverEvent: isHeaderViewHidden " + isHeaderViewHidden +
                    ", isListViewScrolledToTopAndIsHeaderViewHidden " +
                    isListViewScrolledToTopAndIsHeaderViewHidden);
            if (!isHeaderViewHidden || isListViewScrolledToTopAndIsHeaderViewHidden) {
                intercepted = true;
            }
        }
        mLastY = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastY;
                if (!mDragging && Math.abs(deltaY) > mTouchSlop) {
                    mDragging = true;
                }
                Log.d(TAG, "onTouchEvent: mLastY " + mLastY + ", deltaY " + deltaY);
                if (mDragging) {
                    scrollBy(0, -deltaY);
                }
                break;
            case MotionEvent.ACTION_UP:
                mDragging = false;
                mVelocityTracker.computeCurrentVelocity(1000);
                int yVelocity = (int) mVelocityTracker.getYVelocity();
                Log.d(TAG, "onTouchEvent: yVelocity " + yVelocity);
                fling(-yVelocity);
                mVelocityTracker.clear();
                performClick();
                break;
            default:
                break;
        }
        mLastY = y;
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public void scrollTo(int x, int y) {
        Log.d(TAG, "scrollTo: original y " + y);
        if (y > mHeaderViewHeight) {
            y = mHeaderViewHeight;
        }
        if (y < 0) {
            y = 0;
        }
        Log.d(TAG, "scrollTo: y " + y + ", scrollY " + getScrollY());
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
        isHeaderViewHidden = getScrollY() == mHeaderViewHeight;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void fling(int yVelocity) {
        Log.d(TAG, "fling: yVelocity " + yVelocity);
        mScroller.fling(0, getScrollY(), 0, yVelocity, 0, 0, 0, mHeaderViewHeight);
        invalidate();
    }
}
