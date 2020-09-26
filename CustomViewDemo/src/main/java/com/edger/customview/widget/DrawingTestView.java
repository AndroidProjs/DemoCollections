package com.edger.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawingTestView extends View {

    private Path mPath = new Path();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PathMeasure mPathMeasure;

    public DrawingTestView(Context context) {
        super(context);
    }

    public DrawingTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPath.reset();
        mPath.addRect(getWidth() / 2f - 150, getHeight() / 2f - 300, getWidth() / 2f + 150,
                getHeight() / 2f, Path.Direction.CCW);
        mPath.addCircle(getWidth() / 2f, getHeight() / 2f, 150f, Path.Direction.CW);
        mPath.addCircle(getWidth() / 2f, getHeight() / 2f, 400f, Path.Direction.CW);

        // mPathMeasure = new PathMeasure(mPath, false);
        // mPathMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(mPath, mPaint);
    }
}
