package com.sadia.sadiaafrinfarhin.basicbengali;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorsActivity extends CustomBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        //Passing title, subtitle and logo name to set custom toolbar properties
        SetToolbarProperties("Colors","Rong | রং", R.mipmap.colors_activity_logo);

        ArrayList<ColorModel> colors = GetBengaliColors();
        //Binding color list to an adapter
        ColorAdapter adapter = new ColorAdapter(this,R.layout.colors_list_view_item,colors);
        ListView list =(ListView) findViewById(R.id.list_items);
        list.setAdapter(adapter);
    }

    //Populating the list of colors with color objects and returning the list
    public ArrayList<ColorModel> GetBengaliColors() {

        ArrayList<ColorModel> colors = new ArrayList<>();

        List<String> bengaliTranslation = Arrays.asList("লাল","নীল","হলুদ","সবুজ","বেগুনী","আকাশী","বাদামী","গোলাপী","কমলা","সাদা","কালো");
        List<String> bengaliPronunciation = Arrays.asList("Lāl","Nīl","Halud","Shabuj","Beguni","Ākāshī","Bādāmī","Gōlāpī","Kamalā","Shādā","Kālō");
        List<String> englishWord = Arrays.asList("Red","Blue","Yellow","Green","Purple","Cyan","Brown","Pink","Orange","White","Black");

        for (int i = 0; i < bengaliTranslation.size(); i++) {
            colors.add(new ColorModel(bengaliTranslation.get(i),bengaliPronunciation.get(i),englishWord.get(i)));
        }

        return colors;
    }




}
