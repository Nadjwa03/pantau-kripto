package com.example.pantaukripto.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crypto {
    @SerializedName("urls")
    private Urls urls;
    @SerializedName("logo")
    private String logo;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("slug")
    private String slug;
    @SerializedName("description")
    private String description;
    @SerializedName("date_added")
    private String dateAdded;
    @SerializedName("date_launched")
    private String dateLaunched;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("platform")
    private String platform;
    @SerializedName("category")
    private String category;
    @SerializedName("infinite_supply")
    private Boolean infiniteSupply;

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateLaunched() {
        return dateLaunched;
    }

    public void setDateLaunched(String dateLaunched) {
        this.dateLaunched = dateLaunched;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getInfiniteSupply() {
        return infiniteSupply;
    }

    public void setInfiniteSupply(Boolean infiniteSupply) {
        this.infiniteSupply = infiniteSupply;
    }
}
