package com.sadia.sadiaafrinfarhin.basicbengali;

/**
 * Created by Sadia Arfin Farhin on 11/06/2017.
 */

//BaseActivityData class is the base/parent class that holds common variables and properties that is shared amongst other activity data object classes.
public class BaseActivityData {

    private String iconName;
    private String bengaliTranslation;
    private String bengaliPronunciation;
    private String englishWord;
    private String audioName;

    public BaseActivityData(
            String iconName,
            String bengaliTranslation,
            String bengaliPronunciation,
            String englishWord,
            String audioName) {

        this.iconName = iconName;
        this.bengaliTranslation = bengaliTranslation;
        this.bengaliPronunciation = bengaliPronunciation;
        this.englishWord = englishWord;
        this.audioName = audioName;
    }


    public String getIconName() {

        return this.iconName;
    }

    public void setIconName(String iconName) {

        this.iconName = iconName;
    }


    public String getBengaliTranslation() {

        return this.bengaliTranslation;
    }

    public void setBengaliTranslation(String bengaliTranslation) {

        this.bengaliTranslation = bengaliTranslation;
    }

    public String getBengaliPronunciation() {

        return this.bengaliPronunciation;
    }

    public void setBengaliPronunciation(String bengaliPronunciation) {

        this.bengaliPronunciation = bengaliPronunciation;
    }

    public String getEnglishWord() {

        return this.englishWord;
    }

    public void setEnglishWord(String englishWord) {

        this.englishWord = englishWord;
    }

    public String getAudioName() {

        return this.audioName;
    }

    public void setAudioName(String audioName) {

        this.audioName = audioName;
    }
}

