package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.adapters.DogImgAdapter;
import com.bidamcat.petjoa.items.CatImgItem;
import com.bidamcat.petjoa.items.DogImgItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DogImgActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DogImgItem> items= new ArrayList<>();
    DogImgAdapter dogImgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_img);

        recyclerView= findViewById(R.id.dog_recycler);
        dogImgAdapter= new DogImgAdapter(this, items);
        recyclerView.setAdapter(dogImgAdapter);

        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(ActivityCompat.checkSelfPermission(this, permissions[0])== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFromServer();
    }

    void loadDataFromServer(){
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService_Dog retrofitService_dog= retrofit.create(RetrofitService_Dog.class);
        Call<ArrayList<DogImgItem>> call= retrofitService_dog.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<DogImgItem>>() {
            @Override
            public void onResponse(Call<ArrayList<DogImgItem>> call, Response<ArrayList<DogImgItem>> response) {
                items.clear();
                dogImgAdapter.notifyDataSetChanged();

                ArrayList<DogImgItem> list= response.body();
                for(DogImgItem item : list){
                    items.add(0, item);
                    dogImgAdapter.notifyItemInserted(0);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<DogImgItem>> call, Throwable t) {
                Toast.makeText(DogImgActivity.this, "error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void clickDogImgMake(View view) {
        Intent intent= new Intent(this, DogImgMakeActivity.class);
        startActivity(intent);
    }
}