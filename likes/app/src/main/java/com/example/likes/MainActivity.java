package com.example.likes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ImageView unfill, fill;
    String check = "unfill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unfill = (ImageView) findViewById(R.id.unfilled);
        fill = (ImageView) findViewById(R.id.filled);
    }


    public void unfilled(View view) {
        if (check.equals("unfill")) {
            animateHeart(fill);
            fill.setVisibility(View.VISIBLE);
            unfill.setVisibility(View.GONE);

        }
        Snackbar.make(view, "Like it", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        check = "fill";
    }

    public void filled(View view) {
        if (check.equals("fill")) {
            dilikeheart(unfill);
            fill.setVisibility(View.GONE);
            unfill.setVisibility(View.VISIBLE);
        }
        Snackbar.make(view, "Dislike it", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        check = "unfill";
    }

    private void animateHeart(ImageView fill) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        prepareAnimation(scaleAnimation);


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        prepareAnimation(alphaAnimation);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(500);

        fill.startAnimation(animation);
    }


    private static void dilikeheart(ImageView unfill) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        prepareAnimation(scaleAnimation);


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        prepareAnimation(alphaAnimation);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(700);
        unfill.startAnimation(animation);
    }

    private static Animation prepareAnimation(Animation animation) {
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);
        return  animation;
    }
}
