package com.bidamcat.petjoa.itemactivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.pets.RetrofitService_Dog_Ifm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DogIFMItemActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_ifmitem);

        tvTitle= findViewById(R.id.tv_dog_ifm_title_item);
        tvMsg= findViewById(R.id.tv_dog_ifm_msg_item);
        tvName= findViewById(R.id.tv_dog_ifm_name_item);
        tvDate= findViewById(R.id.tv_dog_ifm_date_item);

        Toolbar toolbar= findViewById(R.id.toolbar_dogifm_item);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent= getIntent();

        no= intent.getStringExtra("no");
        String title= intent.getStringExtra("title");
        String name= intent.getStringExtra("name");
        String msg= intent.getStringExtra("msg");
        String date= intent.getStringExtra("date");

        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvName.setText(name);
        tvDate.setText(date);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void clickDelete(View view) {
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("http://kimbidam2.dothome.co.kr/");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        RetrofitService_Dog_Ifm retrofitService_dog_ifm= retrofit.create(RetrofitService_Dog_Ifm.class);
        Call<String> call= retrofitService_dog_ifm.deleteDateToServer(no);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(DogIFMItemActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DogIFMItemActivity.this, "서버오류", Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }
}