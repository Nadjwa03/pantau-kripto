package com.example.pantaukripto.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CryptoInfoResponse {
    @SerializedName("data")
    private Map<String, CryptoDetails> data;

    @SerializedName("status")
    private Status status;

    public Map<String, CryptoDetails> getData() {
        return data;
    }

    public void setData(Map<String, CryptoDetails> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
