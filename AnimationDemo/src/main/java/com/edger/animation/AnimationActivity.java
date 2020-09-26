package com.edger.animation;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.commonmodule.utils.DimenUtils;

public class AnimationActivity extends AppCompatActivity {

    ImageView avatar;

    CircleView circleView;

    CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        // 一
        avatar = findViewById(R.id.avatar);
        // avatar.animate()
        //         .translationX(DimenUtils.dp2px(200))
        //         .translationY(100)
        //         .rotation(180)
        //         .alpha(0.5f)
        //         .setStartDelay(1000)
        //         .start();

        // 二
        circleView = findViewById(R.id.circle_view);
        ObjectAnimator animator = ObjectAnimator.ofFloat(circleView, "radius",
                DimenUtils.dp2px(150));
        animator.setStartDelay(1000);
        animator.start();

        // 四
        cameraView = findViewById(R.id.camera_view);

        // ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(cameraView, "bottomFlip", 45);
        // bottomFlipAnimator.setDuration(1500);
        //
        // ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(cameraView,
        // "flipRotation", 270);
        // flipRotationAnimator.setDuration(1500);
        //
        // ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(cameraView, "topFlip", -45);
        // topFlipAnimator.setDuration(1500);
        //
        // AnimatorSet animatorSet = new AnimatorSet();
        // animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
        // animatorSet.setStartDelay(1000);
        // animatorSet.start();

        // 五
        // PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 45);
        // PropertyValuesHolder flipRotationFlipHolder = PropertyValuesHolder.ofFloat("flipRotation"
        //         , 270);
        // PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -45);
        // ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView,
        //         bottomFlipHolder, flipRotationFlipHolder, topFlipHolder);
        // objectAnimator.setStartDelay(1000);
        // objectAnimator.setDuration(2000);
        // objectAnimator.start();

        // 六
        // float distance = DimenUtils.dp2px(300);
        // Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        // Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.4f * distance);
        // Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * distance);
        // Keyframe keyframe4 = Keyframe.ofFloat(1, 1 * distance);
        // PropertyValuesHolder viewHolder = PropertyValuesHolder.ofKeyframe("translationX",
        //         keyframe1, keyframe2, keyframe3, keyframe4);
        // ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(avatar, viewHolder);
        // animator1.setStartDelay(1000);
        // animator1.setDuration(2000);
        // animator1.start();

        // 七
        avatar.animate()
                .translationY(DimenUtils.dp2px(300))
                .setInterpolator(new DecelerateInterpolator())
                .setStartDelay(1000)
                .setDuration(1000)
                .start();

        avatar.animate()
                .translationX(DimenUtils.dp2px(400))
                .setInterpolator(new AccelerateInterpolator())
                .setStartDelay(2000)
                .setDuration(1000)
                .start();

    }
}