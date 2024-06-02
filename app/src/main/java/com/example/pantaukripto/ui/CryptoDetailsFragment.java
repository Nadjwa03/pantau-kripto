package com.example.pantaukripto.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pantaukripto.R;
import com.example.pantaukripto.api.CmcApiClient;
import com.example.pantaukripto.api.CmcApiService;
import com.example.pantaukripto.components.TagButton;
import com.example.pantaukripto.models.Bookmark;
import com.example.pantaukripto.models.Crypto;
import com.example.pantaukripto.models.CryptoDetails;
import com.example.pantaukripto.models.CryptoInfoResponse;
import com.example.pantaukripto.models.CryptoMapResponse;
import com.example.pantaukripto.utils.BookmarkPreferenceManager;
import com.example.pantaukripto.utils.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoDetailsFragment extends Fragment {
    private CmcApiService apiService;
    private boolean isLoading = false;
    private String cryptoId;
    private CryptoDetails cryptoDetails;

    private ConstraintLayout detailsConstrainsLayout;
    private ImageView cryptoDetailsIconImageView;
    private TextView cryptoDetailsNameTextView, cryptoDetailsSymbolTextView, cryptoDetailsDescriptionTextView, cryptoDetailsDateLaunchedTextView;
    private ImageButton cryptoDetailsBookmarkImageButton;
    private TagButton gitHubTagButton, websiteTagButton;

    private BookmarkPreferenceManager bookmarkPreferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto_details, container, false);
        bookmarkPreferenceManager = new BookmarkPreferenceManager(getActivity());
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiService = CmcApiClient.getInstance();

        cryptoDetailsIconImageView = view.findViewById(R.id.crypto_details_icon_iv);
        cryptoDetailsNameTextView = view.findViewById(R.id.crypto_details_name_tv);
        cryptoDetailsSymbolTextView = view.findViewById(R.id.crypto_details_symbol_tv);
        cryptoDetailsBookmarkImageButton = view.findViewById(R.id.crypto_details_bookmark_button);
        detailsConstrainsLayout = view.findViewById(R.id.details_constraint_layout);
        cryptoDetailsDescriptionTextView = view.findViewById(R.id.crypto_details_description_tv);
        gitHubTagButton = view.findViewById(R.id.github_tag_button);
        websiteTagButton = view.findViewById(R.id.website_tag_button);
        cryptoDetailsDateLaunchedTextView = view.findViewById(R.id.crypto_details_date_launched_tv);

        showDetailsUiElements(false);

        Bundle args = getArguments();
        if (args != null) {
            cryptoId = getArguments().getString("CRYPTO_ID");
            updateBookmarkState();
            load(cryptoId);
        }

        cryptoDetailsBookmarkImageButton.setOnClickListener(v -> {
            toggleBookmark();
        });
    }

    private void load(String cryptoId) {
        isLoading = true;

        Log.d("CRYPTO_ID", cryptoId);

        Call<CryptoInfoResponse> call = apiService.getCryptoInfo(cryptoId);

        call.enqueue(new Callback<CryptoInfoResponse>() {
            @Override
            public void onResponse(Call<CryptoInfoResponse> call, Response<CryptoInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CryptoInfoResponse res = response.body();
                    cryptoDetails = res.getData().get(cryptoId);
                    showDetails(cryptoDetails);
                    showDetailsUiElements(true);
                    // Toast.makeText(getContext(), cryptoDetails.getName() + " data loaded", Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<CryptoInfoResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to fetch more data", Toast.LENGTH_SHORT).show();
                Log.d("CryptoDetailsFragment", "CMC API getCryptoInfo failed", t);
                isLoading = false;
            }
        });
    }

    private void showDetails(CryptoDetails cryptoDetails) {
        Picasso.get().load(cryptoDetails.getLogo()).into(cryptoDetailsIconImageView);
        cryptoDetailsNameTextView.setText(cryptoDetails.getName());
        cryptoDetailsSymbolTextView.setText(cryptoDetails.getSymbol());
        cryptoDetailsDescriptionTextView.setText(cryptoDetails.getDescription());

        if (cryptoDetails.getDateLaunched() == null && cryptoDetails.getDateAdded() == null) {
            cryptoDetailsDateLaunchedTextView.setText("No Date Launched Info");
        } else {
            if (cryptoDetails.getDateLaunched() != null) {
                cryptoDetailsDateLaunchedTextView.setText(Formatter.formatDate(cryptoDetails.getDateLaunched(), "EEEE, dd 'of' MMMM yyyy"));
            } else if (cryptoDetails.getDateAdded() != null) {
                cryptoDetailsDateLaunchedTextView.setText(Formatter.formatDate(cryptoDetails.getDateAdded(), "EEEE, dd 'of' MMMM yyyy"));
            }
        }

        List<String> websiteLinks = cryptoDetails.getUrls().getWebsite();
        if (!websiteLinks.isEmpty()) {
            websiteTagButton.setOnClickListener(v -> {
               openWebView(websiteLinks.get(0));
            });
        }

        List<String> sourceCodeLinks = cryptoDetails.getUrls().getSourceCode();
        if (!sourceCodeLinks.isEmpty()) {
            gitHubTagButton.setOnClickListener(v -> {
                openWebView(sourceCodeLinks.get(0));
            });
        }
    }

    private void showDetailsUiElements(boolean isShow) {
        detailsConstrainsLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void toggleBookmark() {
        if (bookmarkPreferenceManager.isBookmarked(cryptoId)) {
            bookmarkPreferenceManager.removeBookmark(cryptoId);
            Toast.makeText(getContext(), "Removed from bookmarks", Toast.LENGTH_SHORT).show();
        } else {
            bookmarkPreferenceManager.addBookmark(new Bookmark(cryptoId, cryptoDetails.getLogo(), cryptoDetails.getName(), cryptoDetails.getSymbol()));
            Toast.makeText(getContext(), "Added to bookmarks", Toast.LENGTH_SHORT).show();
        }
        updateBookmarkState();
    }

    private void updateBookmarkState() {
        boolean isBookmarked = bookmarkPreferenceManager.isBookmarked(cryptoId);
        cryptoDetailsBookmarkImageButton.setImageResource(isBookmarked ? R.drawable.star_24dp : R.drawable.star_outline_24dp);
    }

    private void openWebView(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        Navigation.findNavController(requireView()).navigate(R.id.action_cryptoDetailsFragment_to_webViewFragment, bundle);
    }
}