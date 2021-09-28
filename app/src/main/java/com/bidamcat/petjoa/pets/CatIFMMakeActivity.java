package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bidamcat.petjoa.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CatIFMMakeActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_ifmmake);

        etTitle= findViewById(R.id.et_title_ifm);
        etMsg= findViewById(R.id.et_msg_ifm);
    }

    public void clickCatIfmOk(View view) {

        String title= etTitle.getText().toString();
        String msg= etMsg.getText().toString();

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();
        RetrofitService2 retrofitService2= retrofit.create(RetrofitService2.class);

        Map<String, String> dataPart= new HashMap<>();
        dataPart.put("title", title);
        dataPart.put("msg", msg);

        Call<String> call= retrofitService2.postDataToServer(dataPart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(CatIFMMakeActivity.this, ""+s, Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CatIFMMakeActivity.this, "error : " +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickCatIfmCancel(View view) {
        finish();
    }
}