package com.sadia.sadiaafrinfarhin.basicbengali;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonWordsActivity extends CustomBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_words);

        //Passing title, subtitle and logo name to set custom toolbar properties
        SetToolbarProperties("Common Words","Shādhāran Shabda | সাধারণ শব্দ", R.mipmap.common_words_activity_logo);

        ArrayList<CommonWordModel> commonWords = GetBengaliCommonWords();
        //Binding common words list to an adapter
        CommonWordsAdapter adapter = new CommonWordsAdapter(this, R.layout.common_words_list_view_item, commonWords);
        ListView list = (ListView) findViewById(R.id.list_items);
        list.setAdapter(adapter);

    }

    //Populating the list of common words with CommonWord objects and returning the list
    public ArrayList<CommonWordModel> GetBengaliCommonWords() {

        ArrayList<CommonWordModel> commonWords = new ArrayList<>();

        List<String> bengaliTranslation = Arrays.asList("হ্যাঁ", "না", "ঠিক আছে", "ভাল", "কাজ", "বাসা", "ক্ষুধার্ত", "সাহায্য", "প্রয়োজন", "বন্ধু");
        List<String> bengaliPronunciation = Arrays.asList("Hā", "Nā", "Ṭhik āchē", "Bhālo", "Kāj", "Bāsha", "Khudhārta", "Shāhājya", "Praẏōjan", "Bandhu");
        List<String> englishWord = Arrays.asList("Yes", "No", "Okay", "Good", "Work", "Home", "Hungry", "Help", "Need", "Friend");

        for (int i = 0; i < bengaliTranslation.size(); i++) {
            commonWords.add(new CommonWordModel(bengaliTranslation.get(i), bengaliPronunciation.get(i), englishWord.get(i)));
        }

        return commonWords;
    }
}