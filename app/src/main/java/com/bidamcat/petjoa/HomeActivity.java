package com.bidamcat.petjoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.bidamcat.petjoa.fragments.HomeFragment;
import com.bidamcat.petjoa.fragments.MyMapFragment;
import com.bidamcat.petjoa.fragments.PetFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments= new Fragment[3];

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragments[0]= new HomeFragment();

        fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.home_fl, fragments[0]);
        transaction.commit();

        bnv= findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction= fragmentManager.beginTransaction();

                transaction.hide(fragments[0]);
                if(fragments[1]!=null) transaction.hide(fragments[1]);
                if(fragments[2]!=null) transaction.hide(fragments[2]);

                switch (item.getItemId()){
                    case R.id.bnv_home:
                        transaction.show(fragments[0]);
                        break;

                    case R.id.bnv_pet:
                        if(fragments[1]==null){
                            fragments[1]= new PetFragment();
                            transaction.add(R.id.home_fl, fragments[1]);
                        }
                        transaction.show(fragments[1]);
                        break;

                    case R.id.bnv_map:
                        if(fragments[2]==null){
                            fragments[2]= new MyMapFragment();
                            transaction.add(R.id.home_fl, fragments[2]);
                        }
                        transaction.show(fragments[2]);
                        break;
                }
                transaction.commit();

                return true;
            }
        });

    }
}