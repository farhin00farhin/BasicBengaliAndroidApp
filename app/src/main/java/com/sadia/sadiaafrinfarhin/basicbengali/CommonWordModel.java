package com.sadia.sadiaafrinfarhin.basicbengali;

/**
 * Created by Sadia Arfin Farhin on 13/06/2017.
 */

public class CommonWordModel extends BaseActivityData {
    public CommonWordModel(
            String bengaliTranslation,
            String bengaliPronunciation,
            String englishWord
    ) {
        super(englishWord, bengaliTranslation, bengaliPronunciation, englishWord, englishWord);

    }
}

