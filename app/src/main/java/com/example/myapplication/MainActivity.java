package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.api.CoinGeckoApi;
import com.example.myapplication.models.CryptoCurrency;
import com.example.myapplication.network.RetrofitClient;
import android.os.Handler;


import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CryptoAdapter cryptoAdapter;
    private EditText etSearch;
    private Handler handler;
    private Runnable refreshDataRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.et_search);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        handler = new Handler();
        refreshDataRunnable = new Runnable() {
            @Override
            public void run() {
                fetchDataFromApi();
                handler.postDelayed(this, 5 * 60 * 1000); // 5 minutos en milisegundos
                Log.i("API", "Datos recibidos");
            }
        };
        handler.post(refreshDataRunnable);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filtra la lista de criptomonedas según la consulta de búsqueda
                cryptoAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && refreshDataRunnable != null) {
            handler.removeCallbacks(refreshDataRunnable);
        }
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
                    if (cryptoAdapter == null) {
                        cryptoAdapter = new CryptoAdapter(MainActivity.this, cryptoList);
                        recyclerView.setAdapter(cryptoAdapter);
                    } else {
                        cryptoAdapter.updateCryptoList(cryptoList);
                        cryptoAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Data retrived", Toast.LENGTH_SHORT).show();

                    }

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
