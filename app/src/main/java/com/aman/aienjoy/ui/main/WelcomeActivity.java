package com.aman.aienjoy.ui.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Window;
import android.widget.ImageView;

import com.aman.aienjoy.R;

import java.util.Random;

public class WelcomeActivity extends Activity {

    ImageView mIVEntry;

    private static final int ANIM_TIME = 2000;

    private static final float SCALE_END = 1.15F;

    private static final int[] Imgs={
            R.drawable.movie_1,R.drawable.movie_2,R.drawable.movie_7,R.drawable.movie_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        mIVEntry = findViewById(R.id.iv_entry);
        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        mIVEntry.setImageResource(Imgs[random.nextInt(Imgs.length)]);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnim();
            }
        },600);
    }


    private void startAnim() {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                WelcomeActivity.this.finish();
            }
        });
    }
}