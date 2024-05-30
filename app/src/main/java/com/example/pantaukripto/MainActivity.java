package com.example.pantaukripto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.pantaukripto.api.CmcApiService;
import com.example.pantaukripto.api.CmcApiClient;
import com.example.pantaukripto.models.CryptoInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CmcApiService cmcApiService = CmcApiClient.getInstance();
        Call<CryptoInfoResponse> call = cmcApiService.getCryptoInfo("1");

        call.enqueue(new Callback<CryptoInfoResponse>() {
            @Override
            public void onResponse(Call<CryptoInfoResponse> call, Response<CryptoInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CryptoInfoResponse res = response.body();
                    Log.d("MainActivity", "Crypto: " + res.getData().get("1").getName());
                }
            }

            @Override
            public void onFailure(Call<CryptoInfoResponse> call, Throwable t) {
                Log.d("MainActivity", "CMC API getCryptoInfo failed", t);
            }
        });
    }
}