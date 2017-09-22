package com.sadia.sadiaafrinfarhin.basicbengali;

/**
 * Created by Sadia Arfin Farhin on 13/06/2017.
 */

public class NumberModel extends BaseActivityData {

    private String banglaIconName;

    public NumberModel(
            String iconName,
            String bengaliTranslation,
            String bengaliPronunciation,
            String englishWord
          ) {super(iconName, bengaliTranslation, bengaliPronunciation, englishWord, iconName);

        this.banglaIconName=iconName;

    }

    public String getBanglaIconName() {

        return this.banglaIconName;
    }

    public void setBanglaIconName(String banglaIconName) {

        this.banglaIconName = banglaIconName;
    }
}
