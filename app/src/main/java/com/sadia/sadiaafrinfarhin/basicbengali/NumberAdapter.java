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
 * Created by Sadia Arfin Farhin on 11/06/2017.
 */

public class NumberAdapter extends ArrayAdapter {
    MediaPlayer mediaPlayer;
    int layoutID;
    Context context;
    ArrayList<NumberModel> numbers = new ArrayList<>();

    public NumberAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<NumberModel> objects) {
        super(context, resource, objects);

        this.layoutID = resource;
        this.context = context;
        this.numbers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListViewItem = getInflatedViewIfNeeded(convertView, parent);

        try {

            //Getting a particular number item from the list of numbers
            final NumberModel currentNumber = numbers.get(position);

            //Setting image resource for image_view_icon and preparing another bangla version of the icon for use in animation
            final ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
            iconImageView.setTag(currentNumber);
            //Stop any animation that may have been playing previously
            iconImageView.clearAnimation();

            final String iconName = "icon" + currentNumber.getIconName();
            final String banglaIconName = "bangla" + currentNumber.getIconName();
            int i = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
            if(i == 0)
            {
                i = context.getResources().getIdentifier("placeholder", "drawable", context.getPackageName());
            }
            iconImageView.setImageResource(i);


            //Setting Bengali and English words and pronunciations in TextViews
            TextView bengaliTranslationTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_bengali_word);
            bengaliTranslationTextView.setText(currentNumber.getBengaliTranslation());

            TextView bengaliPronunciationTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_bengali_pronunciation);
            bengaliPronunciationTextView.setText(currentNumber.getBengaliPronunciation());

            final TextView englishWordTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_english_word);
            englishWordTextView.setText(currentNumber.getEnglishWord());


            final String audio = "audio_" + currentNumber.getAudioName();
            final CardView play = (CardView) currentListViewItem.findViewById(R.id.card_view_numbers);

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
                            Toast.makeText(getContext(), "Unable to play audio", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), "Unable to play audio", Toast.LENGTH_LONG).show();
                    }

                    StartAnimation(iconImageView, banglaIconName, currentNumber, iconName);
                }
            });

        } catch(Exception ex) {
            //Fail quietly because if multiple list items have a problem, then the user will keep getting notified multiple times.
        }
        return currentListViewItem;

    }

    private void StartAnimation(ImageView iconImageView, String banglaIconName, NumberModel currentNumber, String iconName) {
        try {
            //Begin the flip animation
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.shrink_to_middle);
            iconImageView.startAnimation(animation);

            //Exactly when the flip animation is halfway through the first flip, change the icon on the imageview to be the bangla image
            Runnable swapImage = createRunnable(banglaIconName, iconImageView, currentNumber);
            iconImageView.postDelayed(swapImage, 500);

            //Exactly when the flip animation is halfway through the second flip, change the icon on the imageview to be the english image again
            Runnable swapImageBack = createRunnable(iconName, iconImageView, currentNumber);
            iconImageView.postDelayed(swapImageBack, 1500);
        } catch (Exception ex) {
            //Fail quietly since the user shouldn't be interrupted due to an animation not playing
        }
    }


    @NonNull
    private Runnable createRunnable(final String iconName, final ImageView iconImageView, final NumberModel numberToChange) {
        return new Runnable() {
                        @Override
                        public void run() {
                            try {
                                NumberModel attachedNumber = (NumberModel)iconImageView.getTag();
                                //If the tagged number is the same as the numberToChange, set the icon. This prevents the wrong ImageView from being changed after it has been recycled
                                if (numberToChange.equals(attachedNumber)) {
                                    int iconNo = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
                                    iconImageView.setImageResource(iconNo);
                                }
                            }
                            catch (Exception ex) {
                                //Fail quietly since the user shouldn't be interrupted due to an animation not playing
                            }
                        }
                    };
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

