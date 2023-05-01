package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CryptoCurrency implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("current_price")
    private double currentPrice;

    @SerializedName("image")
    private String image;

    @SerializedName("price_change_percentage_24h")
    private double PriceChangePercentage24h;

    @SerializedName("sparkline_in_7d")
    private List<Double> sparklineIn7D;

    @SerializedName("market_cap")
    private double marketCap;

    @SerializedName("market_cap_rank")
    private int marketCapRank;

    @SerializedName("total_volume")
    private double totalVolume;

    @SerializedName("high_24h")
    private double high24h;

    @SerializedName("low_24h")
    private double low24h;

    @SerializedName("circulating_supply")
    private double circulatingSupply;

    @SerializedName("total_supply")
    private double totalSupply;

    @SerializedName("last_updated")
    private String lastUpdated;


    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public int getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(int marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getHigh24h() {
        return high24h;
    }

    public void setHigh24h(double high24h) {
        this.high24h = high24h;
    }

    public double getLow24h() {
        return low24h;
    }

    public void setLow24h(double low24h) {
        this.low24h = low24h;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public List<Double> getSparklineIn7D() {
        return sparklineIn7D;
    }

    public void setSparklineIn7D(List<Double> sparklineIn7D) {
        this.sparklineIn7D = sparklineIn7D;
    }

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