package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.api.CoinGeckoApi;
import com.example.myapplication.models.CryptoCurrency;
import com.example.myapplication.network.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CryptoAdapter cryptoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        String baseUrl = "https://api.coingecko.com/";
        CoinGeckoApi apiService = RetrofitClient.getClient(baseUrl).create(CoinGeckoApi.class);

        Call<List<CryptoCurrency>> call = apiService.getCryptoCurrencies();
        call.enqueue(new Callback<List<CryptoCurrency>>() {
            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CryptoCurrency> cryptoList = response.body();
                    cryptoAdapter = new CryptoAdapter(MainActivity.this, cryptoList);
                    recyclerView.setAdapter(cryptoAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
