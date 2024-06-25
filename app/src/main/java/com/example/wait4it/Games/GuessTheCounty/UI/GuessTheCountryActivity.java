package com.example.wait4it.Games.GuessTheCounty.UI;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.Games.GuessTheCounty.Logic.GuessTheCountryLogic;
import com.example.wait4it.Games.GuessTheCounty.Model.Country;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class GuessTheCountryActivity extends AppCompatActivity {
    private ShapeableImageView[] guessTheCountry_IMG_hearts;
    private MaterialTextView guessTheCountry_LBL_title;
    private MaterialTextView guessTheCountry_LBL_timer;
    private ShapeableImageView guessTheCountry_IMG_flag;
    private MaterialButton[] guessTheCountry_BTN_options;
    private GuessTheCountryLogic guessTheCountryLogic;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);
        findViews();
        setupGame();

    }

    private void setupGame() {
        ImageLoader imageLoader = new ImageLoader(this);
        guessTheCountryLogic = new GuessTheCountryLogic(guessTheCountry_IMG_hearts.length);
        updateUI();
        for (int i = 0; i < guessTheCountry_BTN_options.length; i++) {
            guessTheCountry_BTN_options[i].setOnClickListener(this::onAnswerClicked);
        }
    }

    private void onAnswerClicked(View view) {
        MaterialButton clickedButton = (MaterialButton) view;
        boolean isCorrect = guessTheCountryLogic.checkAnswer(clickedButton.getText().toString());
        if(isCorrect){
            //handle
        }
        else{
            //handle
        }
        if(guessTheCountryLogic.isGameEnded()){
            //handle
        }
        else if(guessTheCountryLogic.isGameEnded()){
            //handle
        }
        else
            updateUI();
    }

    private void updateUI() {
        Country currentCountry = guessTheCountryLogic.getRandomCountryForMainQuestion();
        if(currentCountry!=null)
        {
            imageLoader.load(currentCountry.getFlagImage(), R.drawable.ic_launcher_background, guessTheCountry_IMG_flag);
            List<String> answers = guessTheCountryLogic.getAdditionalAnswers(currentCountry);
            for (int i = 0; i < guessTheCountry_BTN_options.length; i++) {
                guessTheCountry_BTN_options[i].setText(answers.get(i));
            }
        }
    }

    private void findViews() {
        guessTheCountry_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.guessTheCountry_IMG_heart0),
                findViewById(R.id.guessTheCountry_IMG_heart1),
                findViewById(R.id.guessTheCountry_IMG_heart2)
        };
        guessTheCountry_BTN_options = new MaterialButton[]{
            findViewById(R.id.guessTheCountry_LBL_option0),
            findViewById(R.id.guessTheCountry_LBL_option1),
            findViewById(R.id.guessTheCountry_LBL_option2),
            findViewById(R.id.guessTheCountry_LBL_option3)
        };
        guessTheCountry_LBL_title = findViewById(R.id.guessTheCountry_LBL_title);
        guessTheCountry_LBL_timer = findViewById(R.id.guessTheCountry_LBL_timer);
        guessTheCountry_IMG_flag = findViewById(R.id.guessTheCountry_IMG_flag);
    }
}