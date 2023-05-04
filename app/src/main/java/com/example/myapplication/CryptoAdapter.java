package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.models.CryptoCurrency;

import java.util.ArrayList;
import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.ViewHolder> implements Filterable {
    private List<CryptoCurrency> cryptoList;
    private Context context;
    private List<CryptoCurrency> filteredCryptoList;


    public CryptoAdapter(Context context, List<CryptoCurrency> cryptoList) {
        this.context = context;
        this.cryptoList = cryptoList;
        this.filteredCryptoList = new ArrayList<>(cryptoList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crypto_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CryptoCurrency crypto = filteredCryptoList.get(position);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CryptoDetailActivity.class);
                intent.putExtra("selectedCrypto", crypto); // Agrega esta línea para incluir el objeto selectedCrypto
                intent.putExtra("CRYPTO_NAME", crypto.getName());
                intent.putExtra("CRYPTO_IMAGE", crypto.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredCryptoList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<CryptoCurrency> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(cryptoList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (CryptoCurrency crypto : cryptoList) {
                        if (crypto.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(crypto);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredCryptoList.clear();
                filteredCryptoList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public void updateCryptoList(List<CryptoCurrency> newCryptoList) {
        cryptoList.clear();
        cryptoList.addAll(newCryptoList);
        notifyDataSetChanged(); // Notificar que los datos han cambiado
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

