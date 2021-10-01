package com.bidamcat.petjoa.pets;

import com.bidamcat.petjoa.items.CatIFMItem;
import com.bidamcat.petjoa.items.CatImgItem;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService2 {


    @Multipart
    @POST("/CatIfm/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart);


    //서버에서 데이터를 json으로 받아 자동 파싱해서 ArrayList에 바로 넣어주는 기능 메소드
    @GET("/CatIfm/loadDB.php")
    Call<ArrayList<CatIFMItem>> loadDataFromServer();
}