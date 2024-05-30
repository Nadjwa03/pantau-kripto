package com.example.pantaukripto.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CryptoInfoResponse {
    @SerializedName("data")
    private Map<String, Crypto> data;

    @SerializedName("status")
    private Status status;

    public Map<String, Crypto> getData() {
        return data;
    }

    public void setData(Map<String, Crypto> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
