package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.adapters.CatIFMAdapter;
import com.bidamcat.petjoa.items.CatIFMItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CatIFMActivity extends AppCompatActivity {

    ArrayList<CatIFMItem> items= new ArrayList<>();
    CatIFMAdapter catIFMAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_ifmactivity);

        recyclerView= findViewById(R.id.cat_ifm_recycler);
        catIFMAdapter= new CatIFMAdapter(this, items);
        recyclerView.setAdapter(catIFMAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFromServer();
    }

    void loadDataFromServer(){
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService2 retrofitService2= retrofit.create(RetrofitService2.class);
        Call<ArrayList<CatIFMItem>> call= retrofitService2.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<CatIFMItem>>() {
            @Override
            public void onResponse(Call<ArrayList<CatIFMItem>> call, Response<ArrayList<CatIFMItem>> response) {
                items. clear();
                catIFMAdapter.notifyDataSetChanged();

                ArrayList<CatIFMItem> list= response.body();

                for(CatIFMItem item : list){
                    items.add(0,item);
                    catIFMAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CatIFMItem>> call, Throwable t) {
                Toast.makeText(CatIFMActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void clickCatIfmMake(View view) {
        Intent intent= new Intent(this, CatIFMMakeActivity.class);
        startActivity(intent);
    }
}