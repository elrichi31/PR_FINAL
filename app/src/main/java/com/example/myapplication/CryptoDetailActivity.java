package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.myapplication.models.CryptoCurrency;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CryptoDetailActivity extends AppCompatActivity {
    private ImageView ivCryptoDetailLogo;
    private TextView tvCryptoDetailName;
    private LineChart chartCryptoPrice;

    private TextView tvMarketCap;
    private TextView tvMarketCapRank;
    private TextView tvTotalVolume;
    private TextView tvHigh24h;
    private TextView tvLow24h;
    private TextView tvCirculatingSupply;
    private TextView tvTotalSupply;
    private TextView tvLastUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_detail);

        ivCryptoDetailLogo = findViewById(R.id.iv_crypto_detail_logo);
        tvCryptoDetailName = findViewById(R.id.tv_crypto_detail_name);
        chartCryptoPrice = findViewById(R.id.chart_crypto_price);

        tvMarketCap = findViewById(R.id.tv_market_cap);
        tvMarketCapRank = findViewById(R.id.tv_market_cap_rank);
        tvTotalVolume = findViewById(R.id.tv_total_volume);
        tvHigh24h = findViewById(R.id.tv_high_24h);
        tvLow24h = findViewById(R.id.tv_low_24h);
        tvCirculatingSupply = findViewById(R.id.tv_circulating_supply);
        tvTotalSupply = findViewById(R.id.tv_total_supply);
        tvLastUpdated = findViewById(R.id.tv_last_updated);

        CryptoCurrency selectedCrypto = (CryptoCurrency) getIntent().getSerializableExtra("selectedCrypto");

        String cryptoName = getIntent().getStringExtra("CRYPTO_NAME");
        String cryptoImage = getIntent().getStringExtra("CRYPTO_IMAGE");

        tvCryptoDetailName.setText(cryptoName);
        tvMarketCap.setText(String.format("$%,.2f", selectedCrypto.getMarketCap()));
        tvMarketCapRank.setText(String.valueOf(selectedCrypto.getMarketCapRank()));
        tvTotalVolume.setText(String.format("$%,.2f", selectedCrypto.getTotalVolume()));
        tvHigh24h.setText(String.format("$%,.2f", selectedCrypto.getHigh24h()));
        tvLow24h.setText(String.format("$%,.2f", selectedCrypto.getLow24h()));
        tvCirculatingSupply.setText(String.format("%,.2f", selectedCrypto.getCirculatingSupply()));
        tvTotalSupply.setText(String.format("%,.2f", selectedCrypto.getTotalSupply()));

        String lastUpdatedString = selectedCrypto.getLastUpdated();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date lastUpdatedDate = inputFormat.parse(lastUpdatedString);
            String formattedLastUpdated = outputFormat.format(lastUpdatedDate);
            tvLastUpdated.setText(formattedLastUpdated);
        } catch (ParseException e) {
            e.printStackTrace();
            tvLastUpdated.setText(lastUpdatedString); // En caso de error, muestra la fecha sin formato
        }

        Glide.with(this)
                .load(cryptoImage)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivCryptoDetailLogo);

        // Datos ficticios para probar el gráfico
        List<Entry> priceData = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            priceData.add(new Entry(i, (float) (Math.random() * 20000)));
        }
        setupChart(priceData);
        // Datos reales para la grifica
//        List<Entry> priceData = new ArrayList<>();
//        for (int i = 0; i < selectedCrypto.getSparklineIn7D().size(); i++) {
//            priceData.add(new Entry(i, selectedCrypto.getSparklineIn7D().get(i).floatValue()));
//        }
//        setupChart(priceData);
    }


    private void setupChart(List<Entry> priceData) {
        LineDataSet dataSet = new LineDataSet(priceData, "Precio en los últimos 7 días");
        dataSet.setColor(getResources().getColor(R.color.green));
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);
        chartCryptoPrice.setData(lineData);

        XAxis xAxis = chartCryptoPrice.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        chartCryptoPrice.getAxisRight().setEnabled(false);
        chartCryptoPrice.getAxisLeft().setDrawGridLines(false);
        chartCryptoPrice.getDescription().setEnabled(false);
        chartCryptoPrice.invalidate();
    }
}
