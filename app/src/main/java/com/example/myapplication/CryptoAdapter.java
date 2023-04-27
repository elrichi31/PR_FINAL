package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.models.CryptoCurrency;

import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.ViewHolder> {
    private List<CryptoCurrency> cryptoList;
    private Context context;

    public CryptoAdapter(Context context, List<CryptoCurrency> cryptoList) {
        this.context = context;
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crypto_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CryptoCurrency crypto = cryptoList.get(position);
        holder.tvCryptoName.setText(crypto.getName());
        holder.tvCryptoSymbol.setText(crypto.getSymbol());
        holder.tvCryptoPrice.setText(String.format("$%,.2f", crypto.getCurrentPrice()));

        double priceChangePercentage24h = crypto.getPriceChangePercentage24h();
        holder.tvPriceChange24h.setText(String.format("%.2f%%", priceChangePercentage24h));
        if (priceChangePercentage24h >= 0) {
            holder.tvPriceChange24h.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            holder.tvPriceChange24h.setTextColor(context.getResources().getColor(R.color.red));
        }

        Glide.with(context)
                .load(crypto.getImage())
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.ivCryptoLogo);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCryptoLogo;
        TextView tvCryptoName;
        TextView tvCryptoSymbol;
        TextView tvCryptoPrice;
        TextView tvPriceChange24h;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCryptoLogo = itemView.findViewById(R.id.iv_crypto_logo);
            tvCryptoName = itemView.findViewById(R.id.tv_crypto_name);
            tvCryptoSymbol = itemView.findViewById(R.id.tv_crypto_symbol);
            tvCryptoPrice = itemView.findViewById(R.id.tv_crypto_price);
            tvPriceChange24h = itemView.findViewById(R.id.tv_price_change_24h);
        }
    }
}

