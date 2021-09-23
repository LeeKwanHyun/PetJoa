package com.bidamcat.petjoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kakao.sdk.common.util.Utility;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        String keyHash= Utility.INSTANCE.getKeyHash(this);
        Log.i("KeyHash", keyHash);
    }

    public void clickJoin(View view) {
        Intent intent= new Intent(LoginActivity.this, JoinActivity.class);
        startActivity(intent);
    }

    public void clickLogin(View view) {
        Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }

    public void clickKakao(View view) {
        Intent intent= new Intent(LoginActivity.this, KakaoLoginActivity.class);
        startActivity(intent);
    }
}