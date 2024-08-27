package com.example.wait4it.Games.Hangman.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.wait4it.Games.Hangman.Logic.HangmanLogic;
import com.example.wait4it.Games.Hangman.Model.Word;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.Arrays;

public class HangmanActivity extends AppCompatActivity {
    private MaterialTextView hangman_LBL_title;
    private final int INDEX_CORRECTOR_SECOND_ROW = 7;
    private final int INDEX_CORRECTOR_THIRD_ROW = 13;
    private final int INDEX_CORRECTOR_FOURTH_ROW = 20;
    private int seconds;
    private int minutes;
    private ShapeableImageView hangman_IMG_level;
    private MaterialButton[] hangman_BTN_firstRowAtoG = new MaterialButton[7];
    private MaterialButton[] hangman_BTN_secondRowHtoM = new MaterialButton[6];
    private MaterialButton[] hangman_BTN_thirdRowNtoT = new MaterialButton[7];
    private MaterialButton[] hangman_BTN_fourthRowUtoZ = new MaterialButton[6];
    private LinearLayout hangman_LLC_underscores;
    private MaterialTextView[] letterViews;
    private ImageLoader imageLoader;
    private Word currentWord;
    private boolean isCorrect = false;
    private HangmanLogic hangmanLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        findViews();
        initViews();
        setupGame();
        loadImage();
        handleKeyboard();


    }

    private void setupGame() {
        currentWord = hangmanLogic.getRandomWord();
        letterCount = hangmanLogic.getAmountOfLetters();
        if (currentWord != null)
            Log.d("Current", "CurrentWord: " + currentWord + ", As String: " + currentWord.getWordAsString() + ", done? " + currentWord.isDoneAlready());
        else
            Log.d("Current", "currentWord is null");
        setupWordViews(currentWord.getWordAsString());
    }

    private void loadImage() {
        String template = String.format("hangman_%d", hangmanLogic.getWrongAnswers());
        imageLoader.load(getResources().getIdentifier(template, "drawable", getPackageName()), R.drawable.ic_launcher_background, hangman_IMG_level);
    }

    private void handleKeyboard() {
        for (int i = 'A'; i <= 'Z'; i++) {
            int index = i - 'A';
            if (i >= 'A' && i <= 'G') {
                hangman_BTN_firstRowAtoG[index].setOnClickListener(v -> {
                    hangman_BTN_firstRowAtoG[index].setOnClickListener(null);
                    hangman_BTN_firstRowAtoG[index].setClickable(false);
                    isCorrect = hangmanLogic.checkLetter(hangman_BTN_firstRowAtoG[index].getText().toString());
                    if (isCorrect)
                        correctProcedure(hangman_BTN_firstRowAtoG[index]);
                    else
                        wrongProcedure(hangman_BTN_firstRowAtoG[index]);
                    loadImage();
                });
            } else if (i >= 'H' && i <= 'M') {
                hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW].setOnClickListener(v -> {
                    hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW].setOnClickListener(null);
                    hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW].setClickable(false);
                    isCorrect = hangmanLogic.checkLetter(hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW].getText().toString());
                    if (isCorrect)
                        correctProcedure(hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW]);
                    else
                        wrongProcedure(hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW]);
                    loadImage();
                });
            } else if (i >= 'N' && i <= 'T') {
                hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW].setOnClickListener(v -> {
                    hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW].setOnClickListener(null);
                    hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW].setClickable(false);
                    isCorrect = hangmanLogic.checkLetter(hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW].getText().toString());
                    if (isCorrect)
                        correctProcedure(hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW]);
                    else
                        wrongProcedure(hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW]);
                    loadImage();
                });
            } else {
                hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setOnClickListener(v -> {
                    hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setOnClickListener(null);
                    hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setClickable(false);
                    isCorrect = hangmanLogic.checkLetter(hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].getText().toString());
                    if (isCorrect)
                        correctProcedure(hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW]);
                    else
                        wrongProcedure(hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW]);
                    loadImage();
                });
            }
            //20

        }
    }

    private void wrongProcedure(MaterialButton materialButton) {
        materialButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.red_wrong));
        hangmanLogic.incrementWrongAnswers();
        //loadImage();
        if(hangmanLogic.isGameLost()){
            finishGame("Lost");
            return;
        }
        if(hangmanLogic.isGameEnded("Books")){
            finishGame("Won");
        }
        else
        {
            new Handler(Looper.getMainLooper()).postDelayed(()->{
                setupGame();
            },500);
        }
    }

    private void finishGame(String result) {
        if(result.equals("Won"))
            showWinGameDialog(minutes,seconds);
        else
            showLoseGameDialog();
    }

    private void showLoseGameDialog() {
        
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

    private void navigateToGameMenu() {
    }


    //
    private void correctProcedure(MaterialButton materialButton) {
        materialButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green_right));
        fillCorrectLetterInWordViews(materialButton.getText().toString());
    }

    private void fillCorrectLetterInWordViews(String letterAsString) {
        char letter = letterAsString.toLowerCase().charAt(0);
        for (int i = 0; i < currentWord.getWordAsString().length(); i++) {
            if (Character.toLowerCase(currentWord.getWordAsString().charAt(i)) == letter) {
                letterViews[i].setText(String.valueOf(currentWord.getWordAsString().charAt(i)));
            }
        }
    }


    //split sentence to words (" -")
    //concat with " " between each 2 words as long as the output is less than 25 letters
    //assign as a line
    //repeat
    // for sentence is sentences (sentence - LLC in LLC[]

    private void setupWordViews(String sentence) {
        hangman_LLC_underscores.removeAllViews();
        Log.d("Sentence words", sentence);

        // Split the sentence into words including spaces
        String[] words = sentence.split("(?<=\\s)|(?=\\s)");
        Log.d("Before trim words: ", Arrays.toString(words));

        String[] lines = new String[10];
        int maxLetters = 25;
        int currentLine = 0;

        for (String currentWord : words) {
            if (currentWord.equals("-")) {
                lines[++currentLine] = currentWord;
                currentLine++;
                continue;
            }
            if (lines[currentLine] == null || lines[currentLine].isEmpty()) {
                //lines[currentLine] = currentWord.trim();
                lines[currentLine] = currentWord;
            } else if (lines[currentLine].length() + currentWord.length() < maxLetters) {
                //lines[currentLine] += " " + currentWord.trim();
                lines[currentLine] += currentWord;
            } else {
                //lines[++currentLine] = currentWord.trim();
                lines[++currentLine] = currentWord;
            }
        }
        Log.d("Lines:", "line 0: " + lines[0] + "\nline 1: " + lines[1] + "\nline 2: " + lines[2]);

        // Calculate the total required size for letterViews
        int totalCharacters = 0;
        for (String line : lines) {
            if (line != null) {
                totalCharacters += line.length();
            }
        }
        Log.d("Length", ""+totalCharacters);

        letterViews = new MaterialTextView[totalCharacters]; // Correctly sized array
        int i = 0;

        for (String line : lines) {
            if (line != null) {
                Log.d("generate LLC", "line: " + line);

                LinearLayout currentLineLinear = createNewLine();

                for (char letter : line.toCharArray()) {
                    MaterialTextView materialTextView = new MaterialTextView(this);

                    if (letter == ' ') {
                        materialTextView.setText(" ");
                        materialTextView.setWidth(15); // Set a fixed width for spaces
                    } else if (letter == '-') {
                        materialTextView.setText("by");
                    } else {
                        materialTextView.setText("_");
                    }

                    materialTextView.setTextSize(20);
                    materialTextView.setGravity(Gravity.CENTER);

                    currentLineLinear.addView(materialTextView, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

                    // Store the reference in the letterViews array, but only if within bounds
                    if (i < letterViews.length) {
                        letterViews[i++] = materialTextView;
                    }
                }

                hangman_LLC_underscores.addView(currentLineLinear);
            }
        }
    }



    private LinearLayout createNewLine() {
        LinearLayout line = new LinearLayout(this);
        line.setOrientation(LinearLayout.HORIZONTAL);
        line.setGravity(Gravity.CENTER);
        line.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        return line;
    }


    private void initViews() {
        imageLoader = new ImageLoader(this);
        hangmanLogic = new HangmanLogic();
        hangman_LLC_underscores.setOrientation(LinearLayout.VERTICAL);
    }

    private void findViews() {
        hangman_LBL_title = findViewById(R.id.hangman_LBL_title);
        hangman_IMG_level = findViewById(R.id.hangman_IMG_level);
        hangman_LLC_underscores = findViewById(R.id.hangman_LLC_underscores);
        for (int i = 'A'; i <= 'Z'; i++) {
            String template = String.format("hangman_BTN_%c", i);
            int index = i - 'A';
            if (i >= 'A' && i <= 'G') {
                hangman_BTN_firstRowAtoG[index] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            }
            else if (i >= 'H' && i <= 'M') {
                hangman_BTN_secondRowHtoM[index - INDEX_CORRECTOR_SECOND_ROW] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            } else if (i >= 'N' && i <= 'T') {
                hangman_BTN_thirdRowNtoT[index - INDEX_CORRECTOR_THIRD_ROW] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            } else {
                hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW] = findViewById((getResources().getIdentifier(template, "id", getPackageName())));
            }
            //20

        }
    }
}