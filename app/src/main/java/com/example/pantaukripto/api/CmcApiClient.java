package com.example.pantaukripto.api;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CmcApiClient {
    private final static String BASE_URL = "https://pro-api.coinmarketcap.com/";
    private final static String API_KEY = "941bfe44-759b-44f5-9944-5083df30e358";
    private final static int REQUEST_TIMEOUT = 30;
    private static OkHttpClient okHttpClient;
    private static CmcApiService instance;

    public static CmcApiService getInstance() {
        if (okHttpClient == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

            client.addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("X-CMC_PRO_API_KEY", API_KEY);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            okHttpClient = client.build();
        }

        if (instance == null) {
            instance = RetrofitClientFactory.createInstance(BASE_URL, okHttpClient).create(CmcApiService.class);
        }

        return instance;
    }
}
