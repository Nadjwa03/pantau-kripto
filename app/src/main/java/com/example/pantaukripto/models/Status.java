package com.example.pantaukripto.models;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("elapsed")
    private int elapsed;
    @SerializedName("credit_count")
    private int creditCount;
    @SerializedName("notice")
    private String notice;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
