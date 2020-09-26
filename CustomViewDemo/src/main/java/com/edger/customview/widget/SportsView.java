package com.edger.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.edger.commonmodule.utils.DimenUtils;

public class SportsView extends View {

    private float ringWidth = DimenUtils.dp2px(20);
    private float radius = DimenUtils.dp2px(150);
    private int circleColor = Color.parseColor("#09A4AE");
    private int highLightColor = Color.parseColor("#FF4081");

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect bounds = new Rect();
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint.setTextSize(DimenUtils.sp2px(100));
        mPaint.setTypeface(Typeface.SANS_SERIF);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getFontMetrics(mFontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制环
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(circleColor);
        mPaint.setStrokeWidth(ringWidth);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, radius, mPaint);

        // 绘制进度条
        mPaint.setColor(highLightColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2f - radius, getHeight() / 2f - radius,
                getWidth() / 2f + radius, getHeight() / 2f + radius,
                -90, 225, false, mPaint);

        // 绘制文字
        mPaint.setStyle(Paint.Style.FILL);
        // mPaint.getTextBounds("abab", 0, "abab".length(), bounds);
        // int offset = (bounds.top + bounds.bottom) / 2;
        // canvas.drawText("abab", getWidth() / 2f, getHeight() / 2f - offset, mPaint);
        float offset = (mFontMetrics.ascent + mFontMetrics.descent) / 2f;
        canvas.drawText("abab", getWidth() / 2f, getHeight() / 2f - offset, mPaint);

        // 绘制文字：居左
        mPaint.setTextSize(DimenUtils.sp2px(150));
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.getTextBounds("abab", 0, "abab".length(), bounds);
        canvas.drawText("aaaa", -bounds.left, 200, mPaint);
    }
}
