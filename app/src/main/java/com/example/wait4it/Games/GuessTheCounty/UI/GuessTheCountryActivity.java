package com.example.wait4it.Games.GuessTheCounty.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class GuessTheCountryActivity extends AppCompatActivity {
    private ShapeableImageView guessTheCountry_IMG_heart0;
    private ShapeableImageView guessTheCountry_IMG_heart1;
    private ShapeableImageView guessTheCountry_IMG_heart2;
    private MaterialTextView guessTheCountry_LBL_title;
    private MaterialTextView guessTheCountry_LBL_timer;
    private ShapeableImageView guessTheCountry_IMG_flag;
    private MaterialButton guessTheCountry_LBL_option0;
    private MaterialButton guessTheCountry_LBL_option1;
    private MaterialButton guessTheCountry_LBL_option2;
    private MaterialButton guessTheCountry_LBL_option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);
        findViews();
    }

    private void findViews() {
        guessTheCountry_IMG_heart0 = findViewById(R.id.guessTheCountry_IMG_heart0);
        guessTheCountry_IMG_heart1 = findViewById(R.id.guessTheCountry_IMG_heart1);
        guessTheCountry_IMG_heart2 = findViewById(R.id.guessTheCountry_IMG_heart2);
        guessTheCountry_LBL_title = findViewById(R.id.guessTheCountry_LBL_title);
        guessTheCountry_LBL_timer = findViewById(R.id.guessTheCountry_LBL_timer);
        guessTheCountry_IMG_flag = findViewById(R.id.guessTheCountry_IMG_flag);
        guessTheCountry_LBL_option0 = findViewById(R.id.guessTheCountry_LBL_option0);
        guessTheCountry_LBL_option1 = findViewById(R.id.guessTheCountry_LBL_option1);
        guessTheCountry_LBL_option2 = findViewById(R.id.guessTheCountry_LBL_option2);
        guessTheCountry_LBL_option3 = findViewById(R.id.guessTheCountry_LBL_option3);
    }
}