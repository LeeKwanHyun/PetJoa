package com.bidamcat.petjoa.pets;

import com.bidamcat.petjoa.items.DogIFMItem;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitService_Dog_Ifm {


    @Multipart
    @POST("/DogIfm/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart);


    //서버에서 데이터를 json으로 받아 자동 파싱해서 ArrayList에 바로 넣어주는 기능 메소드
    @GET("/DogIfm/loadDB.php")
    Call<ArrayList<DogIFMItem>> loadDataFromServer();

    @GET("/DogIfm/delete.php")
    Call<String> deleteDateToServer(@Query("no") String no);
}
