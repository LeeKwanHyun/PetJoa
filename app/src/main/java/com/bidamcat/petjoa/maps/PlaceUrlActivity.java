package com.bidamcat.petjoa.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bidamcat.petjoa.R;

public class PlaceUrlActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_url);

        //이 액티비티를 실행해준 택배기사님(Intent)에게 가지고 온 추가데이터를 얻어오기
        String placeUrl= getIntent().getStringExtra("place_url");

        wv= findViewById(R.id.wv);
        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());

        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowFileAccess(true);

        //웹뷰에게 장소정보url을 읽어서 보여주도록
        wv.loadUrl(placeUrl);
    }

    //백버튼 눌렀을때 발동하는 메소드
    @Override
    public void onBackPressed() {
        if(wv.canGoBack()) wv.goBack();
        else super.onBackPressed();
    }
}