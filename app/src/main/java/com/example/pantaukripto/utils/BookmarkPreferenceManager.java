package com.example.pantaukripto.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.pantaukripto.models.Bookmark;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookmarkPreferenceManager {
    private static final String PREF_NAME = "bookmarks_prefs";
    private static final String KEY_BOOKMARKS = "bookmarks";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public BookmarkPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    // Save the list of bookmarks
    public void saveBookmarks(List<Bookmark> bookmarks) {
        String bookmarksString = gson.toJson(bookmarks);
        editor.putString(KEY_BOOKMARKS, bookmarksString);
        editor.apply();
    }

    // Retrieve the list of bookmarks
    public List<Bookmark> getBookmarks() {
        String bookmarksString = sharedPreferences.getString(KEY_BOOKMARKS, "");
        if (bookmarksString.isEmpty()) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<Bookmark>>() {}.getType();
        return gson.fromJson(bookmarksString, listType);
    }

    // Add a single bookmark
    public void addBookmark(Bookmark bookmark) {
        List<Bookmark> bookmarks = getBookmarks();
        for (Bookmark b : bookmarks) {
            if (b.getId().equals(bookmark.getId())) {
                return; // Bookmark already exists, don't add it again
            }
        }
        bookmarks.add(bookmark);
        saveBookmarks(bookmarks);
    }

    // Remove a single bookmark
    public void removeBookmark(String id) {
        List<Bookmark> bookmarks = getBookmarks();
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getId().equals(id)) {
                bookmarks.remove(bookmark);
                saveBookmarks(bookmarks);
                break;
            }
        }
    }

    // Check if a bookmark exists
    public boolean isBookmarked(String id) {
        List<Bookmark> bookmarks = getBookmarks();
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}