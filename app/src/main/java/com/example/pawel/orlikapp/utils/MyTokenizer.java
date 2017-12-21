package com.example.pawel.orlikapp.utils;

import android.widget.MultiAutoCompleteTextView;

/**
 * Created by Pawel on 17.12.2017.
 */

public class MyTokenizer implements MultiAutoCompleteTextView.Tokenizer {
    @Override
    public int findTokenStart(CharSequence text, int cursor) {

        for(int i = cursor; i > 0; i--){
            if(text.charAt(i-1) == '.'){
                return i;
            }
        }
        return 0;
    }

    @Override
    public int findTokenEnd(CharSequence text, int cursor) {
        int length = text.length();
        for(int i = cursor; i < length; i++){
            if(text.charAt(i-1) == '.'){
                return i;
            }
        }
        return 0;
    }

    @Override
    public CharSequence terminateToken(CharSequence text) {
        return text;
    }
}
