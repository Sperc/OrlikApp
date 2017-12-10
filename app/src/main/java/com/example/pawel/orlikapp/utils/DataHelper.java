package com.example.pawel.orlikapp.utils;

import android.content.Context;

import com.example.pawel.orlikapp.model.Playground;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 09.12.2017.
 */

public class DataHelper {
    private static List<Suggestion> suggestionSuggest = new ArrayList<>();

    public static void loadData(List<Playground> playgrounds){
        for(Playground p : playgrounds){
            suggestionSuggest.add(new Suggestion(p.getStreetName()+" "+p.getStreetNumber()));
        }

    }

    public interface OnFindSuggestionsListener {
        void onResults(List<Suggestion> results);
    }

    public static void findSuggestions(Context context, String querry, final int limit, final OnFindSuggestionsListener listener) {
        new android.widget.Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Suggestion> suggestionList = new ArrayList<>();
                if (!(charSequence == null || charSequence.length() == 0)) {

                    for (Suggestion suggestion : suggestionSuggest) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(charSequence.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }
                FilterResults results = new FilterResults();

//                Collections.sort(suggestionList, new Comparator<Suggestion>() {
//                    @Override
//                    public int compare(Suggestion suggestion, Suggestion t1) {
//                        return suggestion.
//                    }
//                });
                results.values = suggestionList;
                results.count = suggestionList.size();


                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (listener != null) {
                    listener.onResults((List<Suggestion>) filterResults.values);
                }
            }
        }.filter(querry);
    }

}
