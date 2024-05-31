package com.example.pantaukripto.api;

import com.example.pantaukripto.models.CryptoInfoResponse;
import com.example.pantaukripto.models.CryptoMapResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CmcApiService {
    @GET("v2/cryptocurrency/info")
    Call<CryptoInfoResponse> getCryptoInfo(@Query("id") String id);
    @GET("v1/cryptocurrency/map")
    Call<CryptoMapResponse> getCryptoMap(@Query("sort") String sort, @Query("start") int start, @Query("limit") int limit);
}
