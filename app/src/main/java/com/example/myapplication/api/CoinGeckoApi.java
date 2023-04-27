package com.example.myapplication.api;
import com.example.myapplication.models.CryptoCurrency;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinGeckoApi {
    @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=24h&locale=en")
    Call<List<CryptoCurrency>> getCryptoCurrencies();
}