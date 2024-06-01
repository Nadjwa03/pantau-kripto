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
import com.example.pantaukripto.models.Bookmark;
import com.example.pantaukripto.utils.BookmarkPreferenceManager;

import java.util.List;

public class CryptoBookmarkFragment extends Fragment {
    private BookmarkPreferenceManager bookmarkPreferenceManager;
    private CryptoBookmarkListItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookmarkPreferenceManager = new BookmarkPreferenceManager(getActivity());

        RecyclerView cryptoListRecyclerView = view.findViewById(R.id.crypto_list_bookmark_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cryptoListRecyclerView.setLayoutManager(layoutManager);

        adapter = new CryptoBookmarkListItemAdapter(crypto -> {
            Bundle bundle = new Bundle();
            bundle.putString("CRYPTO_ID", String.valueOf(crypto.getId()));
            Navigation.findNavController(view).navigate(R.id.action_cryptoBookmarkFragment_to_cryptoDetailsFragment, bundle);
        });

        cryptoListRecyclerView.setAdapter(adapter);

        List<Bookmark> bookmarkList = bookmarkPreferenceManager.getBookmarks();
        adapter.setCryptoList(bookmarkList);
    }
}