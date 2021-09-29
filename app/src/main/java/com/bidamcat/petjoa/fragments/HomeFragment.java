package com.bidamcat.petjoa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bidamcat.petjoa.MissingPetMakeActivity;
import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.adapters.CatImgAdapter;
import com.bidamcat.petjoa.adapters.PetMissingAdapter;
import com.bidamcat.petjoa.items.CatImgItem;
import com.bidamcat.petjoa.items.PetMissingItem;
import com.bidamcat.petjoa.pets.CatImgActivity;
import com.bidamcat.petjoa.pets.RetrofitHelper;
import com.bidamcat.petjoa.pets.RetrofitService;
import com.bidamcat.petjoa.pets.RetrofitService_PetMissing;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<PetMissingItem> items= new ArrayList<>();
    private PetMissingAdapter petMissingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.home_recycler);
        recyclerView.setHasFixedSize(true);
        petMissingAdapter= new PetMissingAdapter(getContext(), items);
        recyclerView.setAdapter(petMissingAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

        loadDataFromServer();
    }


    void loadDataFromServer(){

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService_PetMissing retrofitServicePetMissing= retrofit.create(RetrofitService_PetMissing.class);
        Call<ArrayList<PetMissingItem>> call= retrofitServicePetMissing.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<PetMissingItem>>() {
            @Override
            public void onResponse(Call<ArrayList<PetMissingItem>> call, Response<ArrayList<PetMissingItem>> response) {

                items.clear();
                petMissingAdapter.notifyDataSetChanged();

                ArrayList<PetMissingItem> list= response.body();

                for(PetMissingItem item : list){
                    items.add(0, item);
                    petMissingAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PetMissingItem>> call, Throwable t) {
                Toast.makeText(getActivity(), "error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton floatingActionButton= view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), MissingPetMakeActivity.class);
                startActivity(intent);
            }
        });
    }



}
