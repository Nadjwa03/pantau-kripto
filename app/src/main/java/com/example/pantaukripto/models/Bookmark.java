package com.example.pantaukripto.models;

public class Bookmark {
    private String id;
    private String logo;
    private String name;
    private String symbol;

    public Bookmark(String id, String logo, String name, String symbol) {
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
}
