package com.example.pantaukripto.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoMapResponse {
    @SerializedName("data")
    List<Crypto> data;

    @SerializedName("status")
    private Status status;

    public List<Crypto> getData() {
        return data;
    }

    public void setData(List<Crypto> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
