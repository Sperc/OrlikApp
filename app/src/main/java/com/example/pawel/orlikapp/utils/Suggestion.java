package com.example.pawel.orlikapp.utils;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by Pawel on 09.12.2017.
 */

@SuppressLint("ParcelCreator")
public class Suggestion implements SearchSuggestion {
    private String suggest;

    public Suggestion(String suggest) {
        this.suggest = suggest;
    }

    @Override
    public String getBody() {
        return suggest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
