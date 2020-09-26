package com.edger.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.edger.commonmodule.utils.DimenUtils;

public class DashboardView extends View {

    private static final int ANGLE = 120;

    public static final float RADIUS = DimenUtils.dp2px(150);

    public static final float INDICATOR_LENGHT = DimenUtils.dp2px(100);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path dash = new Path();

    PathEffect mPathEffect;

    PathMeasure mPathMeasure;

    public DashboardView(Context context) {
        super(context);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DimenUtils.dp2px(2));
        dash.addRect(0, 0, DimenUtils.dp2px(2), DimenUtils.dp2px(10), Path.Direction.CW);

        Path arc = new Path();
        arc.addArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS, getWidth() / 2f + RADIUS
                , getHeight() / 2f + RADIUS, 90 + ANGLE / 2f, 360 - ANGLE);
        mPathMeasure = new PathMeasure(arc, false);
        mPathEffect = new PathDashPathEffect(dash,
                (mPathMeasure.getLength() - DimenUtils.dp2px(2)) / 20, 0,
                PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS, getWidth() / 2f + RADIUS
                , getHeight() / 2f + RADIUS, 90 + ANGLE / 2f, 360 - ANGLE, false, mPaint);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS, getWidth() / 2f + RADIUS
                , getHeight() / 2f + RADIUS, 90 + ANGLE / 2f, 360 - ANGLE, false, mPaint);
        mPaint.setPathEffect(null);

        canvas.drawLine(getWidth() / 2f, getHeight() / 2f,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * INDICATOR_LENGHT + getWidth() / 2f,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * INDICATOR_LENGHT + getHeight() / 2f, mPaint);
    }

    int getAngleFromMark(int mark) {
        return (int) (90 + ANGLE / 2f + (360 - ANGLE) / 20f * mark);
    }
}
