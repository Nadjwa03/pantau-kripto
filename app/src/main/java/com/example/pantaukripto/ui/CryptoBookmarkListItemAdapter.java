package com.example.pantaukripto.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantaukripto.R;
import com.example.pantaukripto.models.Bookmark;
import com.example.pantaukripto.models.Crypto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CryptoBookmarkListItemAdapter extends RecyclerView.Adapter<CryptoBookmarkListItemAdapter.CryptoListItemViewHolder> {
    private List<Bookmark> cryptoList;

    public CryptoBookmarkListItemAdapter(CryptoBookmarkListItemAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setCryptoList(List<Bookmark> cryptoList) {
        this.cryptoList = cryptoList;
        notifyDataSetChanged();
    }

    public void appendCryptoList(List<Bookmark> cryptoList) {
        if (this.cryptoList == null) {
            this.setCryptoList(cryptoList);
            return;
        }
        int startPosition = this.cryptoList.size();
        this.cryptoList.addAll(cryptoList);
        notifyItemRangeInserted(startPosition, cryptoList.size());
    }

    @NonNull
    @Override
    public CryptoBookmarkListItemAdapter.CryptoListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crypto_list_item, parent, false);
        return new CryptoBookmarkListItemAdapter.CryptoListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoBookmarkListItemAdapter.CryptoListItemViewHolder holder, int position) {
        Bookmark crypto = cryptoList.get(position);
        holder.cryptoItemNameTextView.setText(crypto.getName());
        holder.cryptoItemSymbolTextView.setText(crypto.getSymbol());
        String iconUrl = "https://s2.coinmarketcap.com/static/img/coins/64x64/" + crypto.getId() + ".png";
        Picasso.get().load(iconUrl).into(holder.cryptoItemIconImageView);
        holder.itemView.setOnClickListener(v -> onItemClickListener.handle(crypto));
    }

    @Override
    public int getItemCount() {
        return cryptoList == null ? 0 : cryptoList.size();
    }

    private CryptoBookmarkListItemAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void handle(Bookmark crypto);
    }

    static class CryptoListItemViewHolder extends RecyclerView.ViewHolder {
        TextView cryptoItemNameTextView, cryptoItemSymbolTextView;
        ImageView cryptoItemIconImageView;

        public CryptoListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cryptoItemNameTextView = itemView.findViewById(R.id.crypto_item_name_tv);
            cryptoItemSymbolTextView = itemView.findViewById(R.id.crypto_item_symbol_tv);
            cryptoItemIconImageView = itemView.findViewById(R.id.crypto_item_icon_iv);
        }
    }
}
