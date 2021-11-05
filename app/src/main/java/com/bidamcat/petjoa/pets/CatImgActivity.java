package com.bidamcat.petjoa.pets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.adapters.CatImgAdapter;
import com.bidamcat.petjoa.items.CatImgItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CatImgActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CatImgItem> items= new ArrayList<>();
    CatImgAdapter catImgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_img);

        recyclerView= findViewById(R.id.cat_recycler);
        catImgAdapter= new CatImgAdapter(this, items);
        recyclerView.setAdapter(catImgAdapter);

        Toolbar toolbar= findViewById(R.id.toolbar_catimg);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(ActivityCompat.checkSelfPermission(this, permissions[0])== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFromServer();
    }

    void loadDataFromServer(){
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<CatImgItem>> call= retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<CatImgItem>>() {
            @Override
            public void onResponse(Call<ArrayList<CatImgItem>> call, Response<ArrayList<CatImgItem>> response) {
                items.clear();
                catImgAdapter.notifyDataSetChanged();

                ArrayList<CatImgItem> list= response.body();
                for(CatImgItem item : list){
                    items.add(0, item);
                    catImgAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CatImgItem>> call, Throwable t) {
                Toast.makeText(CatImgActivity.this, "error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickCatImgMake(View view) {
        Intent intent= new Intent(this, CatImgMakeActivity.class);
        startActivity(intent);
    }
}

