package com.bidamcat.petjoa.maps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

interface RetrofitService {

    @Headers("Authorization: KakaoAK 90c122c594315f7fdb6cca289688b7f1")
    @GET("/v2/local/search/keyword.json")
    Call<String> searchPlaceByString(@Query("query") String query, @Query("x") String longitude, @Query("y") String latitude);

    @Headers("Authorization: KakaoAK 90c122c594315f7fdb6cca289688b7f1")
    @GET("/v2/local/search/keyword.json")
    Call<SearchLocalApiResponse> searchPlace(@Query("query") String query, @Query("x") String longitude, @Query("y") String latitude);
}
