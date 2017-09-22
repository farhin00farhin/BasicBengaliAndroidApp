package com.sadia.sadiaafrinfarhin.basicbengali;

/**
 * Created by Sadia Arfin Farhin on 13/06/2017.
 */

public class ColorModel extends BaseActivityData {

    //Hard coding icon name as Color activity will only be using one icon and the color of the icon will be modified with filters to create variation.

    public ColorModel(
            String bengaliTranslation,
            String bengaliPronunciation,
            String englishWord
    ) {
        super("color_icon", bengaliTranslation, bengaliPronunciation, englishWord, englishWord);

    }
}
