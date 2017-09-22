package com.sadia.sadiaafrinfarhin.basicbengali;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends CustomBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Passing title, subtitle and logo name to set custom toolbar properties
        SetToolbarProperties("Numbers", "Shaṅkhyā | সংখ্যা", R.mipmap.number_activity_logo);

        ArrayList<NumberModel> numbers = GetBengaliNumbers();
        //Binding number list to an adapter
        NumberAdapter adapter = new NumberAdapter(this, R.layout.numbers_list_view_item, numbers);
        ListView list = (ListView) findViewById(R.id.list_items);
        list.setAdapter(adapter);
    }

    //Populating the list of numbers with number objects and returning the list
    public ArrayList<NumberModel> GetBengaliNumbers() {

        ArrayList<NumberModel> numbers = new ArrayList<>();

        List<String> bengaliTranslation = Arrays.asList("শূন্য (০)", "এক (১)", "দুই (২)", "তিন (৩)", "চার (৪)", "পাঁচ (৫)", "ছয় (৬)", "সাত (৭)", "আট (৮)", "নয় (৯)");
        List<String> bengaliPronunciation = Arrays.asList("Shūn'ya", "Ēk", "Du'i", "Tin", "Chār", "Pāch", "Chaẏ", "Shāt", "Āṭ", "Naẏ");
        List<String> englishWord = Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine");

        for (int i = 0; i < bengaliTranslation.size(); i++) {
            numbers.add(new NumberModel(Integer.toString(i), bengaliTranslation.get(i), bengaliPronunciation.get(i), englishWord.get(i)));
        }

        return numbers;
    }


}
