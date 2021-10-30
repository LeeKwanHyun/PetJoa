package com.bidamcat.petjoa.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.bidamcat.petjoa.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

import org.jetbrains.annotations.NotNull;

public class AnimalHospitalMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_hospital_map);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int checkResult= checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkResult== PackageManager.PERMISSION_DENIED){
                String[] permissions= new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions, 0);
            }
        }

        FragmentManager fragmentManager= getSupportFragmentManager();
        SupportMapFragment mapFragment= (SupportMapFragment)fragmentManager.findFragmentById(R.id.frag_map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {

                UiSettings settings= googleMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

                settings.setMyLocationButtonEnabled(true);

                if(ActivityCompat.checkSelfPermission( AnimalHospitalMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AnimalHospitalMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                googleMap.setMyLocationEnabled(true);
            }
        });

    }
}