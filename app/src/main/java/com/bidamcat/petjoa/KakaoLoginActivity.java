package com.bidamcat.petjoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class KakaoLoginActivity extends AppCompatActivity {

    CircleImageView civ;
    TextView tvNickname;
    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);

        civ= findViewById(R.id.civ);
        tvNickname= findViewById(R.id.tv_nickname);
        tvEmail= findViewById(R.id.tv_email);

        UserApiClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                Toast.makeText(KakaoLoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                    @Override
                    public Unit invoke(User user, Throwable throwable) {
                        if(user!=null){
                            G.id= user.getId();

                            G.nickname= user.getKakaoAccount().getProfile().getNickname();
                            G.profileUrl= user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                            G.email= user.getKakaoAccount().getEmail();

                            List<String> scope= new ArrayList<>();
                            if(user.getKakaoAccount().getEmailNeedsAgreement()) scope.add("account_email");

                            if(scope.size()>0){
                                UserApiClient.getInstance().loginWithNewScopes(KakaoLoginActivity.this, scope, new Function2<OAuthToken, Throwable, Unit>() {
                                    @Override
                                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                                       G.email= user.getKakaoAccount().getEmail();
                                       tvEmail.setText(G.email);

                                        return null;
                                    }
                                });
                            }

                            tvNickname.setText(G.nickname);
                            tvEmail.setText(G.email);
                            Glide.with(KakaoLoginActivity.this).load(G.profileUrl).into(civ);


                        }

                        return null;
                    }
                });
                
                if(oAuthToken != null){
                    
                }else{
                    Toast.makeText(KakaoLoginActivity.this, "사용자정보 요청 실패", Toast.LENGTH_SHORT).show();
                }
                
                
                return null;
            }
        });

    }


    public void clickHome(View view) {
        Intent intent= new Intent(KakaoLoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void clickLogout(View view) {
        UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
            @Override
            public Unit invoke(Throwable throwable) {
                Toast.makeText(KakaoLoginActivity.this, "로그아웃 되셨습니다.", Toast.LENGTH_SHORT).show();

                //기존정보들 모두 제거
                G.id=-1;
                G.nickname= null;
                G.email= null;
                G.profileUrl= null;

                //화면도 모두 제거
                tvNickname.setText("닉네임");
                tvEmail.setText("이메일");
                Glide.with(KakaoLoginActivity.this).load(R.mipmap.ic_launcher).into(civ);

                finish();
                return null;
            }
        });
    }
}