package com.example.wait4it.Games.GuessTheCounty.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.wait4it.Games.GuessTheCounty.Logic.GuessTheCountryLogic;
import com.example.wait4it.Games.GuessTheCounty.Model.Country;
import com.example.wait4it.Games.UI.GamesMainPage;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.Arrays;
import java.util.List;

public class GuessTheCountryActivity extends AppCompatActivity {
    private ShapeableImageView[] guessTheCountry_IMG_hearts;
    private MaterialTextView guessTheCountry_LBL_title;
    private MaterialTextView guessTheCountry_LBL_score;
    private MaterialTextView guessTheCountry_LBL_timer;
    private ShapeableImageView guessTheCountry_IMG_flag;
    private MaterialButton[] guessTheCountry_BTN_options;
    private GuessTheCountryLogic guessTheCountryLogic;
    private ImageLoader imageLoader;
    private Country currentCountry;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;
    private long startTime;
    int seconds=0;
    int minutes=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_country);
        findViews();
        configureTimer();
        setupGame();

    }

    private void configureTimer() {
        startTime = System.currentTimeMillis();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                seconds = (int) (millis / 1000);
                minutes = seconds / 60;
                seconds = seconds % 60;
                guessTheCountry_LBL_timer.setText(String.format("%02d:%02d", minutes,seconds));
                timerHandler.postDelayed(timerRunnable, 0);
            }
        };

    }

    @Override
    protected void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    private void setupGame() {
        imageLoader = new ImageLoader(this);
        guessTheCountryLogic = new GuessTheCountryLogic(guessTheCountry_IMG_hearts.length);
        updateUI();
        for (int i = 0; i < guessTheCountry_BTN_options.length; i++)
            guessTheCountry_BTN_options[i].setOnClickListener(this::onAnswerClicked);

    }

    private void onAnswerClicked(View view) {
        MaterialButton clickedButton = (MaterialButton) view;
        int clickedIndex = Arrays.asList(guessTheCountry_BTN_options).indexOf(clickedButton);
        boolean isCorrect = clickedIndex == guessTheCountryLogic.getCorrectAnswer();

       /* guessTheCountry_BTN_options[guessTheCountryLogic.getCorrectAnswer()]
                .setBackgroundColor(ContextCompat.getColor(this, R.color.green_right));*/
        if(isCorrect)
        {
            clickedButton.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.green_right));
        }
        else
        {
            clickedButton.
                    setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.red_wrong));
            guessTheCountry_BTN_options[guessTheCountryLogic.getCorrectAnswer()]
                    .setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.green_right));
        }
        new Handler(Looper.getMainLooper()).postDelayed(()->{
        },500);
        if(isCorrect){
            guessTheCountryLogic.incrementScore();
            //Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else{
            //clickedButton.setBackgroundColor(ContextCompat.getColor(this,R.color.red_wrong));
            guessTheCountry_IMG_hearts[guessTheCountryLogic.getWrongAnswers()].setVisibility(View.INVISIBLE);
            guessTheCountryLogic.incrementWrongAnswers();
            if(guessTheCountryLogic.isGameLost()){
                finishGame("Lost");
                return;
            }
        }
        if(guessTheCountryLogic.isGameEnded()){
            finishGame("Won");
        }
        else
        {
            new Handler(Looper.getMainLooper()).postDelayed(()->{
                updateUI();
            },500);
        }
    }




    private void updateUI() {
        guessTheCountryLogic.incrementQuestionIndex();
        for (MaterialButton button : guessTheCountry_BTN_options) {
            button.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.white));
        }
        guessTheCountry_LBL_score.setText(String.format("Score: %d", guessTheCountryLogic.getScore()));
        currentCountry = guessTheCountryLogic.getRandomCountryForMainQuestion();
        if(currentCountry!=null)
        {
            List<String> answers = guessTheCountryLogic.getAdditionalAnswers(currentCountry);
            imageLoader.load(currentCountry.getFlagImage(), R.drawable.ic_launcher_background, guessTheCountry_IMG_flag);
            for (int i = 0; i < guessTheCountry_BTN_options.length; i++) {
                guessTheCountry_BTN_options[i].setText(answers.get(i));
            }
        }
    }
    private void finishGame(String result) {
        stopTimer();
        if(result.equals("Won"))
            showWinGameDialog(minutes,seconds);
        else
            showLoseGameDialog();



    }

    private void stopTimer() {
        timerHandler.removeCallbacks(timerRunnable);;
    }

    private void showLoseGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage(String.format("You lost the game after %d flags", guessTheCountryLogic.getQuestionIndex()));
        builder.setPositiveButton("OK", (dialog, id) -> {
            navigateToGameMenu();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showWinGameDialog(int minutes, int seconds) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String timeMessage;
        if(minutes > 0)
            timeMessage = String.format("You won the game in %d minutes and %d seconds!", minutes,seconds);
        else
            timeMessage = String.format("You won the game in %d seconds!",seconds);

        builder.setTitle("Congratulations!");
        builder.setMessage(timeMessage);
        builder.setPositiveButton("OK", (dialog, id) -> {
            navigateToGameMenu();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showEndGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String timeMessage;
        if(minutes > 0)
            timeMessage = String.format("You cleared the level in %d minutes and %d seconds!", minutes,seconds);
        else
            timeMessage = String.format("You cleared the level in %d seconds!",seconds);

        builder.setTitle("Congratulations!");
        timeMessage = "end";
        builder.setMessage(timeMessage);
        builder.setPositiveButton("OK", (dialog, id) -> {
            navigateToGameMenu();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void navigateToGameMenu() {
        Intent intent = new Intent(this, GamesMainPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        guessTheCountry_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.guessTheCountry_IMG_heart0),
                findViewById(R.id.guessTheCountry_IMG_heart1),
                findViewById(R.id.guessTheCountry_IMG_heart2)
        };
        guessTheCountry_BTN_options = new MaterialButton[]{
            findViewById(R.id.guessTheCountry_BTN_option0),
            findViewById(R.id.guessTheCountry_BTN_option1),
            findViewById(R.id.guessTheCountry_BTN_option2),
            findViewById(R.id.guessTheCountry_BTN_option3)
        };
        guessTheCountry_LBL_title = findViewById(R.id.guessTheCountry_LBL_title);
        guessTheCountry_LBL_timer = findViewById(R.id.guessTheCountry_LBL_timer);
        guessTheCountry_IMG_flag = findViewById(R.id.guessTheCountry_IMG_flag);
        guessTheCountry_LBL_score = findViewById(R.id.guessTheCountry_LBL_score);
    }
}