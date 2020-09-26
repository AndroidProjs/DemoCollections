package com.edger.customview.widget;

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
import com.edger.customview.R;

public class CameraView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

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
        camera.rotateX(45);
        camera.setLocation(0, 0, DimenUtils.getZlocationForCamera(-6));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制上半部分
        canvas.save();
        canvas.translate(100 + 600 / 2f, 100 + 600 / 2f);
        canvas.rotate(-20);
        canvas.clipRect(-600, -600, 600, 0);
        canvas.rotate(20);
        canvas.translate(-(100 + 600 / 2f), -(100 + 600 / 2f));
        canvas.drawBitmap(getAvatar(600), 100, 100, paint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(100 + 600 / 2f, 100 + 600 / 2f);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-600, 0, 600, 600);
        canvas.rotate(20);
        canvas.translate(-(100 + 600 / 2f), -(100 + 600 / 2f));
        canvas.drawBitmap(getAvatar(600), 100, 100, paint);
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
