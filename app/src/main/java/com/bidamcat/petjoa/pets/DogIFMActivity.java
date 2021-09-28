package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.adapters.DogIFMAdapter;
import com.bidamcat.petjoa.items.DogIFMItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DogIFMActivity extends AppCompatActivity {

    ArrayList<DogIFMItem> items= new ArrayList<>();
    RecyclerView recyclerView;
    DogIFMAdapter dogIFMAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_ifmactivity);

        recyclerView= findViewById(R.id.dog_ifm_recycler);
        dogIFMAdapter= new DogIFMAdapter(this, items);
        recyclerView.setAdapter(dogIFMAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFromServer();
    }

    void loadDataFromServer(){
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService_Dog_Ifm retrofitServiceDogIfm= retrofit.create(RetrofitService_Dog_Ifm.class);
        Call<ArrayList<DogIFMItem>> call= retrofitServiceDogIfm.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<DogIFMItem>>() {
            @Override
            public void onResponse(Call<ArrayList<DogIFMItem>> call, Response<ArrayList<DogIFMItem>> response) {
                items.clear();
                dogIFMAdapter.notifyDataSetChanged();

                ArrayList<DogIFMItem> list= response.body();
                for( DogIFMItem item : list){
                    items.add(0, item);
                    dogIFMAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DogIFMItem>> call, Throwable t) {
                Toast.makeText(DogIFMActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickDogIfmMake(View view) {
        Intent intent= new Intent(this, DogIFMMakeActivity.class);
        startActivity(intent);
    }
}