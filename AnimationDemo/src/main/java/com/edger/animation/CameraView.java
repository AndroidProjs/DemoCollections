package com.edger.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.edger.commonmodule.utils.DimenUtils;

public class CameraView extends View {

    public static final float PADDING = DimenUtils.dp2px(200);
    public static final float IMAGE_WIDTH = DimenUtils.dp2px(200);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        camera.setLocation(0, 0, DimenUtils.getZlocationForCamera(-6));
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制上半部分
        canvas.save();
        canvas.translate(PADDING + IMAGE_WIDTH / 2f, PADDING + IMAGE_WIDTH / 2f);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2f), -(PADDING + IMAGE_WIDTH / 2f));
        canvas.drawBitmap(getAvatar((int) IMAGE_WIDTH), PADDING, PADDING, paint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(PADDING + IMAGE_WIDTH / 2f, PADDING + IMAGE_WIDTH / 2f);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2f), -(PADDING + IMAGE_WIDTH / 2f));
        canvas.drawBitmap(getAvatar((int) IMAGE_WIDTH), PADDING, PADDING, paint);
        canvas.restore();
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
