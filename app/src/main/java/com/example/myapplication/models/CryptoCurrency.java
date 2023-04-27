package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("current_price")
    private double currentPrice;

    @SerializedName("price_change_percentage_24h")
    private double PriceChangePercentage24h;


    public double getPriceChangePercentage24h() {
        return PriceChangePercentage24h;
    }

    public void setPriceChangePercentage24h(double priceChangePercentage24h) {
        PriceChangePercentage24h = priceChangePercentage24h;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    private String image;

    // Genera los métodos getter y setter correspondientes.

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}