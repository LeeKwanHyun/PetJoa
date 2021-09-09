package com.bidamcat.petjoa.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PetTabPagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments= new Fragment[2];

    public PetTabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments[0]= new CatFragment();
        fragments[1]= new DogFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
