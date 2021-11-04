package com.bidamcat.petjoa.itemactivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.pets.RetrofitService_Dog;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DogImgItemActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_img_item);

        iv= findViewById(R.id.iv_dog_img_item);
        tvMsg= findViewById(R.id.tv_dog_msg_item);
        tvName= findViewById(R.id.tv_dog_name_item);
        tvDate= findViewById(R.id.tv_dog_date_item);

        Toolbar toolbar= findViewById(R.id.toolbar_dogimg_item);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent= getIntent();

        no= intent.getStringExtra("no");
        String name= intent.getStringExtra("name");
        String msg= intent.getStringExtra("msg");
        String date= intent.getStringExtra("date");
        String imgUrl= intent.getStringExtra("file");
        Toast.makeText(this, ""+no, Toast.LENGTH_SHORT).show();

        tvMsg.setText(msg);
        tvName.setText(name);
        tvDate.setText(date);

        Glide.with(this).load(imgUrl).into(iv);
        //Toast.makeText(this, ""+imgUrl, Toast.LENGTH_SHORT).show();

        iv.setTransitionName("file");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void click123(View view) {
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("http://kimbidam2.dothome.co.kr/");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();
        Toast.makeText(this, ""+no, Toast.LENGTH_SHORT).show();

        RetrofitService_Dog retrofitService_dog= retrofit.create(RetrofitService_Dog.class);
        Call<String> call= retrofitService_dog.deleteDateToServer(no);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(DogImgItemActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DogImgItemActivity.this, "서버오류", Toast.LENGTH_SHORT).show();
            }
        });
    }
}