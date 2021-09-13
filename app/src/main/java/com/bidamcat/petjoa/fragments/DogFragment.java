package com.bidamcat.petjoa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.pets.DogIFMActivity;
import com.bidamcat.petjoa.pets.DogImgActivity;

public class DogFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_tab_dog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btnDogImg= view.findViewById(R.id.dog_img);
        btnDogImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), DogImgActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnDogIfm= view.findViewById(R.id.dog_ifm);
        btnDogIfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), DogIFMActivity.class);
                startActivity(intent);
            }
        });
    }
}
