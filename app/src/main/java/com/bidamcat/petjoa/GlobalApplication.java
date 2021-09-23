package com.bidamcat.petjoa;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //카카오 SDK초기화
        KakaoSdk.init(this, "92f760492890fdc1e0e8166b4c2cfeb7"); //네이티브 앱키

    }
}
