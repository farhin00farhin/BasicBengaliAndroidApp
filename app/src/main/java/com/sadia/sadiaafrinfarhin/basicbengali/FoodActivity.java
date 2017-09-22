package com.sadia.sadiaafrinfarhin.basicbengali;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodActivity extends CustomBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //Passing title, subtitle and logo name to set custom toolbar properties
        SetToolbarProperties("Food", "Khābār | খাবার", R.mipmap.food_activity_logo);

        ArrayList<FoodModel> food = GetBengaliFood();
        //Binding food list to an adapter
        FoodAdapter adapter = new FoodAdapter(this, R.layout.food_list_view_item, food);
        ListView list = (ListView) findViewById(R.id.list_items);
        list.setAdapter(adapter);
    }

    //Populating the list of foods with food objects and returning the list
    public ArrayList<FoodModel> GetBengaliFood() {

        ArrayList<FoodModel> foods = new ArrayList<>();

        List<String> bengaliTranslation = Arrays.asList("ভাত", "রুটি", "পানি", "ডাল", "মাছ", "মুরগি", "মরিচ", "মিষ্টি", "সবজি", "দুধ");
        List<String> bengaliPronunciation = Arrays.asList("Bhaat", "Ruṭi", "Pāni", "Ḍāl", "Māch", "Murgi", "Morich", "Miṣhṭi", "Śābji", "Dudh");
        List<String> englishWord = Arrays.asList("Rice", "Bread", "Water", "Pulses", "Fish", "Chicken", "Chili", "Sweets", "Vegetable", "Milk");

        for (int i = 0; i < bengaliTranslation.size(); i++) {
            foods.add(new FoodModel( bengaliTranslation.get(i), bengaliPronunciation.get(i), englishWord.get(i)));
        }

        return foods;
    }
}