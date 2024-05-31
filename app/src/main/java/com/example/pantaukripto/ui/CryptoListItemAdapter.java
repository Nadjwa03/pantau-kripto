package com.example.pantaukripto.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantaukripto.R;
import com.example.pantaukripto.models.Crypto;

import java.util.List;

public class CryptoListItemAdapter extends RecyclerView.Adapter<CryptoListItemAdapter.CryptoListItemViewHolder> {
    private List<Crypto> cryptoList;

    public CryptoListItemAdapter(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setCryptoList(List<Crypto> cryptoList) {
        this.cryptoList = cryptoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CryptoListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crypto_list_item, parent, false);
        return new CryptoListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoListItemViewHolder holder, int position) {
        Crypto crypto = cryptoList.get(position);
        holder.cryptoItemNameTextView.setText(crypto.getName());
        holder.itemView.setOnClickListener(v -> onItemClickListener.handle(crypto));
    }

    @Override
    public int getItemCount() {
        return cryptoList == null ? 0 : cryptoList.size();
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void handle(Crypto crypto);
    }

    static class CryptoListItemViewHolder extends RecyclerView.ViewHolder {
        TextView cryptoItemNameTextView;

        public CryptoListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cryptoItemNameTextView = itemView.findViewById(R.id.crypto_item_name_tv);
        }
    }
}
