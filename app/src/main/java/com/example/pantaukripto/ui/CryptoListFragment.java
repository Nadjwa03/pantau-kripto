package com.example.pantaukripto.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pantaukripto.R;
import com.example.pantaukripto.api.CmcApiClient;
import com.example.pantaukripto.api.CmcApiService;
import com.example.pantaukripto.models.Crypto;
import com.example.pantaukripto.models.CryptoMapResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoListFragment extends Fragment {
    private final int LIMIT = 50;
    private final int MAX_DATA = 5000;
    private CryptoListItemAdapter adapter;
    private CmcApiService apiService;
    private int start = 1;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView cryptoListRecyclerView = view.findViewById(R.id.crypto_list_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cryptoListRecyclerView.setLayoutManager(layoutManager);

        adapter = new CryptoListItemAdapter(new CryptoListItemAdapter.OnItemClickListener() {
            @Override
            public void handle(Crypto crypto) {
                Bundle bundle = new Bundle();
                bundle.putString("cryptoId", String.valueOf(crypto.getId()));
                Navigation.findNavController(view).navigate(R.id.action_cryptoListFragment_to_cryptoDetailsFragment, bundle);
            }
        });

        cryptoListRecyclerView.setAdapter(adapter);

        apiService = CmcApiClient.getInstance();

        cryptoListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (start > MAX_DATA) {
                    Toast.makeText(getContext(), "No more data to load", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    load();
                }
            }
        });

        load();
    }

    private void load() {
        isLoading = true;

        Call<CryptoMapResponse> call = apiService.getCryptoMap("cmc_rank", start, LIMIT);

        call.enqueue(new Callback<CryptoMapResponse>() {
            @Override
            public void onResponse(Call<CryptoMapResponse> call, Response<CryptoMapResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CryptoMapResponse res = response.body();
                    List<Crypto> cryptoList = res.getData();
                    adapter.appendCryptoList(cryptoList);
                    start += LIMIT;
                    Toast.makeText(getContext(), "More crypto data loaded", Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<CryptoMapResponse> call, Throwable t) {
                Log.d("MainActivity", "CMC API getCryptoInfo failed", t);
                isLoading = false;
            }
        });
    }
}