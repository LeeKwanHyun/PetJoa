package com.bidamcat.petjoa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.pets.CatIFMActivity;
import com.bidamcat.petjoa.pets.CatImgActivity;

public class CatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_tab_cat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btnCatImg= view.findViewById(R.id.cat_img);
        btnCatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), CatImgActivity.class);
                startActivity(intent);

            }
        });

        ImageButton btnCatIfm= view.findViewById(R.id.cat_ifm);
        btnCatIfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), CatIFMActivity.class);
                startActivity(intent);
            }
        });

    }
}
