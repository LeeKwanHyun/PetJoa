package com.bidamcat.petjoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    ImageView petjoa, dogcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        petjoa= findViewById(R.id.intro_petjoa);
        dogcat= findViewById(R.id.intro_dogcat);

        Animation ani= AnimationUtils.loadAnimation(this,R.anim.intro_petjoa);
        petjoa.startAnimation(ani);


        Animation ani2= AnimationUtils.loadAnimation(this,R.anim.intro_dogcat);
        dogcat.startAnimation(ani2);

        ani2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent= new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}