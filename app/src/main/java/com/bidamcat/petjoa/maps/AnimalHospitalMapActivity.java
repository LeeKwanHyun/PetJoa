package com.bidamcat.petjoa.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AnimalHospitalMapActivity extends AppCompatActivity {

    int choiceID = R.id.choice_hospital;

    //Kakao 키워드 로컬 검색 API의 요청 파라미터
    //1. 장소명(검색어)
    String searchQuery = "동물병원";
    //2. 내 위치정보객체( 위도,경도 정보 보유한 객체)
    Location mylocation;

    //Fused Location 관리 객체
    FusedLocationProviderClient locationProviderClient;

    //검색결과 데이터를 가진 객체
    public SearchLocalApiResponse searchLocalApiResponse;

    TabLayout tabLayout;

    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_hospital_map);

        //툴바를 제목줄(ActionBar)로 설정하기
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SearchListFragment를 우선 화면에 붙이기
        getSupportFragmentManager().beginTransaction().add(R.id.container, new SearchListFragment()).commit();

        //탭레이아웃의 탭버튼을 클릭하는 것을 처리
        tabLayout = findViewById(R.id.layout_tab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("LIST")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchListFragment()).commit();

                } else if (tab.getText().equals("MAP")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchMapFragment()).commit();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        etSearch= findViewById(R.id.et_search);
        //소프트키보드의 검색버튼(액션버튼)을 눌렀을때!!
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //검색어 얻어오기
                searchQuery= etSearch.getText().toString();
                searchPlace();
                return false;
            }
        });


        //우선 내 위치 사용에 대한 허용 동적퍼미션 [다이얼로그로 허락을 득하는 퍼미션]
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        int checkResult = checkSelfPermission(permissions[0]);
        if (checkResult == PackageManager.PERMISSION_DENIED) requestPermissions(permissions, 10);
        else requestMyLocation();

    }//onCreate method..

    //requestPermissions()의 다이얼로그에서 선택하면 자동으로 실행되는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 내 위치 얻어오기
            requestMyLocation();
        } else {
            Toast.makeText(this, "내 위치정보를 제공하지 않아 검색기능을 온전히 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //내 위치 얻어내는 기능 코드 메소드
    void requestMyLocation() {
        //Google Map에서 사용하고 있는 내위치검색 API 라이브러리 적용 [Fused Location API : play-services-location]

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //실시간 위치검색 조건 설정
        LocationRequest request = LocationRequest.create();
        request.setInterval(1000); //위치정보갱신 간격
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//우선순위

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationProviderClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper());
    }

    //위치정보 받았을때 반응하는 객체
    LocationCallback locationCallback= new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);

            //위치결과객체로 부터 내위치 정보 얻기
            mylocation= locationResult.getLastLocation();

            //Toast.makeText(MainActivity.this, ""+mylocation.getLatitude(), Toast.LENGTH_SHORT).show();

            //위치얻어왔으니 더이상 업데이트 하지 마세요.
            locationProviderClient.removeLocationUpdates(locationCallback);

            //위치정보를 얻었으니 카카오 키워드 로컬 검색 시작
            searchPlace();
        }
    };


    //카카오 키워드 로컬 검색 API 호출 기능 메소드
    void searchPlace(){

        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("https://dapi.kakao.com");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();

        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<SearchLocalApiResponse> call= retrofitService.searchPlace(searchQuery, mylocation.getLongitude()+"", mylocation.getLatitude()+"");
        call.enqueue(new Callback<SearchLocalApiResponse>() {
            @Override
            public void onResponse(Call<SearchLocalApiResponse> call, Response<SearchLocalApiResponse> response) {
                searchLocalApiResponse= response.body();

                //ListFragment가 먼저 보이도록..
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchListFragment()).commit();

                //TabLayout을 List탭으로 변경
                tabLayout.getTabAt(0).select();

//                PlaceMeta meta= searchLocalApiResponse.meta;
//                List<Place> documents= searchLocalApiResponse.documents;
//
//                new AlertDialog.Builder(MainActivity.this).setMessage(meta.total_count+"\n"+documents.get(0).place_name+"\n"+documents.get(0).distance).show();
            }

            @Override
            public void onFailure(Call<SearchLocalApiResponse> call, Throwable t) {
                Toast.makeText(AnimalHospitalMapActivity.this, "서버에 오류가 있습니다.\n잠시뒤에 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
            }
        });


    }


    //옵션메뉴를 만들어 주는 기능 메소드
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.actionbar_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    public void clickChoice(View view) {
        findViewById(choiceID).setBackgroundResource(R.drawable.bg_choice);

        view.setBackgroundResource(R.drawable.bg_choice_select);
        choiceID= view.getId();

        switch (choiceID){
            case R.id.choice_hospital: searchQuery="동물병원"; break;
            case R.id.choice_dog_cafe: searchQuery="강아지카페"; break;
            case R.id.choice_cat_cafe: searchQuery="고양이카페"; break;

        }

        //검색작업 요청
        searchPlace();

        //editText의 글씨 제거
        etSearch.setText("");
        etSearch.clearFocus();
    }
}