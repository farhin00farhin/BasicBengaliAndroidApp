package com.sadia.sadiaafrinfarhin.basicbengali;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sadia Arfin Farhin on 10/06/2017.
 */

public class CustomBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Passing instructional toast for user
        Toast.makeText(this, "Click anywhere to play the pronunciation audio", Toast.LENGTH_LONG).show();
    }

    public void SetToolbarProperties(String title, String subtitle, int logo) {

        // creating custom toolbar and setting its background color and title
        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar((toolbar));

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
        toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.appColor));
        toolbar.setSubtitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.grey));
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.lines));
        getSupportActionBar().setIcon(logo);
    }

    //Common animation for entering and exiting an activity
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
