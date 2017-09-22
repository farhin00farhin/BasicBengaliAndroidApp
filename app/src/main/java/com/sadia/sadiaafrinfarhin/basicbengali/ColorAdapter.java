package com.sadia.sadiaafrinfarhin.basicbengali;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sadia Arfin Farhin on 11/06/2017.
 */

public class ColorAdapter extends ArrayAdapter {
    MediaPlayer mediaPlayer;
    int layoutID;
    Context context;

    ArrayList<ColorModel> colors = new ArrayList<>();

    public ColorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ColorModel> objects) {
        super(context, resource, objects);

        this.layoutID = resource;
        this.context = context;
        this.colors = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentListViewItem = getInflatedViewIfNeeded(convertView, parent);

        try {

            //Getting a particular color item from the list of colors
            ColorModel currentColor = colors.get(position);

            //Setting image resource for color_icon
            final ImageView iconImageView = (ImageView) currentListViewItem.findViewById(R.id.image_view_icon);
            iconImageView.setImageResource(R.drawable.color_icon);

            final String englishColor = currentColor.getEnglishWord().toLowerCase();

            //Setting a different background color for the ImageView if the color is white and a transparent background if it's not
            if (englishColor.equals("white")) {
                iconImageView.setBackgroundResource(R.color.grey);
            } else {
                iconImageView.setBackgroundColor(Color.parseColor("#00ffffff"));
            }

            //Getting the color from the resource file and converting it to a hex value to use it as a filter. The filter will change the color of the icon to match the name of the color
            int c = context.getResources().getIdentifier(englishColor, "color", context.getPackageName());
            final int actualColor = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, c)));
            iconImageView.setColorFilter(actualColor);

            //Creating an overlay with the current color to animate later
            final RelativeLayout colorOverly = (RelativeLayout)currentListViewItem.findViewById(R.id.color_overlay);
            colorOverly.setBackgroundColor(actualColor);

            //Stop any animation that may have been playing previously
            colorOverly.clearAnimation();
            colorOverly.setAlpha(0);

            //Setting Bengali and English words in TextViews
            TextView bengaliTranslationTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_bengali_word);
            bengaliTranslationTextView.setText(currentColor.getBengaliTranslation());

            TextView bengaliPronunciationTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_bengali_pronunciation);
            bengaliPronunciationTextView.setText(currentColor.getBengaliPronunciation());

            final TextView englishWordTextView = (TextView) currentListViewItem.findViewById(R.id.text_view_english_word);
            englishWordTextView.setText(currentColor.getEnglishWord());

            final String audio = "audio_" + currentColor.getAudioName().toLowerCase();
            final CardView play = (CardView) currentListViewItem.findViewById(R.id.card_view_colors);

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
                        Animation animation = AnimationUtils.loadAnimation(context, R.anim.card_fade);
                        colorOverly.setAlpha(1);
                        colorOverly.startAnimation(animation);
                    } catch (Exception ex) {
                        //Do nothing if the animation can't play, because failing quietly is better then interrupting the user in this case.
                    }
                }
            });
        }
        catch(Exception ex) {
            //Need to fail quietly because if multiple list items have a problem, then the user will keep getting notified multiple times.
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
