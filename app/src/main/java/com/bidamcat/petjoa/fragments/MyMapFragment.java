package com.bidamcat.petjoa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.maps.AnimalHospitalListActivity;
import com.bidamcat.petjoa.maps.AnimalHospitalMapActivity;

import java.util.ArrayList;

public class MyMapFragment extends Fragment {

    ArrayList<String> items= new ArrayList<>();

    ListView list;
    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mymap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btnList= view.findViewById(R.id.animal_hospital_list);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), AnimalHospitalListActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnMap= view.findViewById(R.id.animal_hospital_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), AnimalHospitalMapActivity.class);
                startActivity(intent);
            }
        });

    }
}
