package com.edger.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.edger.commonmodule.utils.DimenUtils;
import com.edger.customview.R;

public class AvatarView extends View {

    private int avatarWidth = (int) DimenUtils.dp2px(300);

    private int avatarEdgerWidth = (int) DimenUtils.dp2px(2);

    private int avatarPadding = (int) DimenUtils.dp2px(30);

    private Bitmap avatarBitmap;

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF saveArea = new RectF();
    Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        avatarBitmap = getAvatar(avatarWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        saveArea.set(avatarPadding, avatarPadding, avatarPadding + avatarWidth,
                avatarPadding + avatarWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(avatarPadding, avatarPadding, avatarPadding + avatarWidth,
                avatarPadding + avatarWidth, mPaint);
        int saved = canvas.saveLayer(saveArea, mPaint);
        canvas.drawOval(avatarPadding + avatarEdgerWidth, avatarPadding + avatarEdgerWidth,
                avatarPadding + avatarWidth - avatarEdgerWidth,
                avatarPadding + avatarWidth - avatarEdgerWidth, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(avatarBitmap, avatarPadding, avatarPadding, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);

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
