package com.edger.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.edger.commonmodule.utils.DimenUtils;

public class PieChartView extends View {

    public static final float RADIUS = DimenUtils.dp2px(150);

    public static final float RADIUS_OFFSET = DimenUtils.dp2px(20);

    public static final int PULL_OUT_INDEX = 2;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    RectF bounds = new RectF();

    int[] angles = {60, 100, 120, 80};

    int[] colors = {Color.parseColor("#2979FF"), Color.parseColor("#C2185B"), Color.parseColor(
            "#009688"), Color.parseColor("#FF8F00")};

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS,
                getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            mPaint.setColor(colors[i]);
            canvas.save();
            if (i == PULL_OUT_INDEX) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2f)) * RADIUS_OFFSET,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2f)) * RADIUS_OFFSET);
            }
            canvas.drawArc(bounds, currentAngle, angles[i], true, mPaint);
            canvas.restore();
            currentAngle += angles[i];
        }
    }

}
