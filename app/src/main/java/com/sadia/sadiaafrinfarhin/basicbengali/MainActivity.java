package com.sadia.sadiaafrinfarhin.basicbengali;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {


    MainActivityViewHolder vh;
    AlertDialog dialog;

    View.OnClickListener cardClickHandler = new View.OnClickListener() {
        public void onClick(View view) {

            if (view == vh.cardViewWelcome) {
                dialog.show();
            }

            if (view == vh.cardViewNumbers) {
                Intent numbersActivity = new Intent(getBaseContext(), NumbersActivity.class);
                startActivity(numbersActivity);
            }

            if (view == vh.cardViewCommonWords) {
                Intent commonWordsActivity = new Intent(getBaseContext(), CommonWordsActivity.class);
                startActivity(commonWordsActivity);
            }

            if (view == vh.cardViewColors) {
                Intent colorsActivity = new Intent(getBaseContext(), ColorsActivity.class);
                startActivity(colorsActivity);
            }

            if (view == vh.cardViewFood) {
                Intent foodActivity = new Intent(getBaseContext(), FoodActivity.class);
                startActivity(foodActivity);
            }

            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creating custom toolbar and setting its background color and title
        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar((toolbar));
        getSupportActionBar().setTitle("Learn Basic Bengali");
        getSupportActionBar().setIcon(R.mipmap.logo);

        //Finding views by id and instantiating and calling onClickListener
        vh = new MainActivityViewHolder();

        vh.cardViewNumbers = (CardView) findViewById(R.id.card_view_numbers);
        vh.cardViewNumbers.setOnClickListener(cardClickHandler);

        vh.cardViewCommonWords = (CardView) findViewById(R.id.card_view_common_words);
        vh.cardViewCommonWords.setOnClickListener(cardClickHandler);

        vh.cardViewColors = (CardView) findViewById(R.id.card_view_colors);
        vh.cardViewColors.setOnClickListener(cardClickHandler);

        vh.cardViewFood = (CardView) findViewById(R.id.card_view_food);
        vh.cardViewFood.setOnClickListener(cardClickHandler);

        vh.cardViewWelcome = (CardView) findViewById(R.id.card_view_welcome);
        vh.cardViewWelcome.setOnClickListener(cardClickHandler);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bengali (বাংলা) is a language spoken mostly in Bangladesh and some parts of India. 'Basic Bengali' helps you learn correct meaning and pronunciation of very basic yet useful words.\n\nClick around to learn some Bengali now!").setTitle("Hi there!");
        dialog = builder.create();

        animate();

    }
//Animation for each category CardView
    public void animate() {

        animateView(vh.cardViewNumbers,500);
        animateView(vh.cardViewCommonWords,800);
        animateView(vh.cardViewColors,600);
        animateView(vh.cardViewFood,1000);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome_card_fade);
        vh.cardViewWelcome.setAnimation(animation);
    }

    private void animateView(View view,long offset) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.card_entry_effect);
        animation.setStartOffset(offset);
        view.startAnimation(animation);
    }

}

//Separate class that holds all view objects
class MainActivityViewHolder {
    CardView cardViewNumbers;
    CardView cardViewCommonWords;
    CardView cardViewColors;
    CardView cardViewFood;
    CardView cardViewWelcome;
}

