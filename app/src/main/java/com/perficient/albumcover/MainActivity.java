package com.perficient.albumcover;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        float density  = getResources().getDisplayMetrics().density;
        int height = (int)(displayMetrics.heightPixels / density);
        int width = (int)(displayMetrics.widthPixels / density);

        ImageView cover = findViewById(R.id.cover);
        ImageView border = findViewById(R.id.border);
        MultiTransformation<Bitmap> multi = new MultiTransformation<>(
                new BlurTransformation(15),
                new CropTransformation(
                        new Croppable((width-240)/2,
                                (height-240)/2,
                                240, 240,
                                width, height)),
                new CircleCrop()
                );

        Glide.with(this)
                .load(R.drawable.frozen_bg)
                .override(width, height)
                .apply(RequestOptions.bitmapTransform(multi)).into(border);

        Glide.with(this)
                .load("https://i.pinimg.com/originals/ba/a1/21/baa1219f33c105c4980e38c6e59f56f6.jpg")
                .apply(RequestOptions.circleCropTransform()).into(cover);


        Button toggle = findViewById(R.id.toggle);
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.rotate);
        animator.setTarget(cover);
        toggle.setOnClickListener(v -> {
            if (!animator.isStarted()) {
                animator.start();
            } else if (animator.isPaused()) {
                animator.resume();
            } else {
                animator.pause();
            }
        });


    }
}