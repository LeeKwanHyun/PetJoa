package com.bidamcat.petjoa.pets;

import com.bidamcat.petjoa.items.CatImgItem;
import com.bidamcat.petjoa.items.PetMissingItem;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitService_PetMissing {


    @Multipart
    @POST("/PetMissing/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart,
                                  @Part MultipartBody.Part filePart);


    //서버에서 데이터를 json으로 받아 자동 파싱해서 ArrayList에 바로 넣어주는 기능 메소드
    @GET("/PetMissing/loadDB.php")
    Call<ArrayList<PetMissingItem>> loadDataFromServer();

    @GET("/PetMissing/delete.php")
    Call<String> deleteDateToServer(@Query("no") String no);
}
