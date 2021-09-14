package com.bidamcat.petjoa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bidamcat.petjoa.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PetFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 pager;
    PetTabPagerAdapter tabAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout= view.findViewById(R.id.pet_tab);
        pager= view.findViewById(R.id.pet_pager);
        tabAdapter= new PetTabPagerAdapter(getActivity());
        pager.setAdapter(tabAdapter);

        //pager.setUserInputEnabled(false);

        String[] tabTitles= new String[]{"CAT","DOG"};
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        }).attach();

    }
}
