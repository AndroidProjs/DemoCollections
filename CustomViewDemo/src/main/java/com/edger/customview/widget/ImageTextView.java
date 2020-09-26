package com.edger.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.edger.commonmodule.utils.DimenUtils;
import com.edger.customview.R;

public class ImageTextView extends View {

    private float ringWidth = DimenUtils.dp2px(20);
    private float radius = DimenUtils.dp2px(150);
    private int circleColor = Color.parseColor("#09A4AE");
    private int highLightColor = Color.parseColor("#FF4081");

    private String text = "This class represents the basic building block for user interface " +
            "components. A View occupies a rectangular area on the screen and is responsible for " +
            "drawing and event handling. View is the base class for widgets, which are used to " +
            "create interactive UI components (buttons, text fields, etc.). The ViewGroup " +
            "subclass is the base class for layouts, which are invisible containers that hold " +
            "other Views (or other ViewGroups) and define their layout properties.";

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    float[] curWidth = new float[1];

    Bitmap mBitmap;

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mBitmap = getAvatar((int) DimenUtils.dp2px(100));
        mPaint.setTextSize(DimenUtils.sp2px(16));
        mTextPaint.setTextSize(DimenUtils.sp2px(12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // StaticLayout staticLayout = new StaticLayout(text, mTextPaint, getWidth(),
        //         Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        // staticLayout.draw(canvas);

        canvas.drawBitmap(mBitmap, getWidth() - DimenUtils.dp2px(100), 120, mPaint);
        int index = mPaint.breakText(text, true, getWidth(), curWidth);
        canvas.drawText(text, 0, index, 0, 50, mPaint);
        canvas.drawText(text, 0, index, 0, 50 + mPaint.getFontSpacing(), mPaint);
        canvas.drawText(text, 0, index, 0, 50 + mPaint.getFontSpacing() * 2, mPaint);
    }

    private Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.avatar, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avatar, options);
    }

}
