package com.sadia.sadiaafrinfarhin.basicbengali;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sadia Arfin Farhin on 12/06/2017.
 */

public class CommonWordsAdapter extends ArrayAdapter {
    MediaPlayer mediaPlayer;
    int layoutID;
    Context context;
    ArrayList<CommonWordModel> commonWords = new ArrayList<>();

    public CommonWordsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<CommonWordModel> objects) {
        super(context, resource, objects);

        this.layoutID = resource;
        this.context = context;
        this.commonWords = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentListViewItem = getInflatedViewIfNeeded(convertView, parent);
        try {
            //getting a particular commonWord item from the list of common words
            CommonWordModel currentCommonWord = commonWords.get(position);

            //Setting image resource for image_view_icon
            final ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
            // stop any animation that may have been playing previously
            iconImageView.clearAnimation();
            final String iconName = currentCommonWord.getIconName().toLowerCase();
            int i = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
            if(i == 0)
            {
                i = context.getResources().getIdentifier("placeholder", "drawable", context.getPackageName());
            }
            iconImageView.setImageResource(i);

            //Setting Bengali and English words and pronunciations in TextViews
            TextView bengaliTranslationTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_bengali_word);
            bengaliTranslationTextView.setText(currentCommonWord.getBengaliTranslation());

            TextView bengaliPronunciationTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_bengali_pronunciation);
            bengaliPronunciationTextView.setText(currentCommonWord.getBengaliPronunciation());

            TextView englishWordTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_english_word);
            englishWordTextView.setText(currentCommonWord.getEnglishWord());


            final String audio = "audio_" + currentCommonWord.getAudioName().toLowerCase();
            CardView play = (CardView) currentListViewItem.findViewById(R.id.card_view_common_words);

            //Setting the CardView click handler to play audio
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int i = context.getResources().getIdentifier(audio, "raw", context.getPackageName());
                        if (i != 0) {
                            //Using MediaPlayer to play the audio file
                            if (mediaPlayer != null)
                                mediaPlayer.release();
                            mediaPlayer = MediaPlayer.create(context, i);
                            mediaPlayer.start();
                        }
                        else {
                            Toast.makeText(getContext(), "Unable to find audio file", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), "Unable to play audio", Toast.LENGTH_LONG).show();
                    }

                    try {
                        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake);
                        iconImageView.startAnimation(animation);
                    } catch (Exception ex) {
                        // do nothing if the animation can't play, because failing quietly is better then interrupting the user in this case.
                    }
                }
            });
        } catch(Exception ex) {
            // Need to fail quietly because if multiple list items have a problem, then the user will keep getting notified multiple times.
        }

        return currentListViewItem;
    }

    private View getInflatedViewIfNeeded(@Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListViewItem = convertView;

        //If the view is loading for the first time, then inflate and return it.
        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(layoutID, parent, false);
        }

        //Otherwise, just return the old view.
        return currentListViewItem;
    }
}
