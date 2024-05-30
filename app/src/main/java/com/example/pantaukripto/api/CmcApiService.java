package com.example.pantaukripto.api;

import com.example.pantaukripto.models.CryptoInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CmcApiService {
    @GET("v2/cryptocurrency/info")
    Call<CryptoInfoResponse> getCryptoInfo(@Query("id") String id);
}
