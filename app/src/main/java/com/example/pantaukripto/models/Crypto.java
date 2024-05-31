package com.example.pantaukripto.models;

import com.google.gson.annotations.SerializedName;

public class Crypto {
    @SerializedName("id")
    private int id;
    @SerializedName("rank")
    private int rank;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("slug")
    private String slug;
    @SerializedName("is_active")
    private int isActive;
    @SerializedName("first_historical_data")
    private String firstHistoricalData;
    @SerializedName("last_historical_data")
    private String lastHistoricalData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public String getFirstHistoricalData() {
        return firstHistoricalData;
    }

    public void setFirstHistoricalData(String firstHistoricalData) {
        this.firstHistoricalData = firstHistoricalData;
    }

    public String getLastHistoricalData() {
        return lastHistoricalData;
    }

    public void setLastHistoricalData(String lastHistoricalData) {
        this.lastHistoricalData = lastHistoricalData;
    }
}
