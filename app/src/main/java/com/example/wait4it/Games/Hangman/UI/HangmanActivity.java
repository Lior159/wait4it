package com.example.wait4it.Games.Hangman.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

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
        /*String word = "Liverpool";
        String word2 = "Manchester United";
        setupWordViews(word2);*/
        setupGame();
        loadImage();
        handleKeyboard();


    }

    private void setupGame() {
        currentWord = hangmanLogic.getRandomWord();
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
        loadImage();
    }


    //
    private void correctProcedure(MaterialButton materialButton) {
        materialButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.green_right));
        fillCorrectLetterInWordViews(materialButton.getText().toString());
    }

    private void fillCorrectLetterInWordViews(String letterAsString) {
        char letter = letterAsString.toLowerCase().charAt(0);
        for (int i = 0; i < currentWord.getWordAsString().length(); i++) {
            // Skip if the current entry is null (i.e., a space or a separator)
            if (letterViews[i] == null) continue;

            // Check if the character in the word matches the guessed letter
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

        String[] Words = sentence.split(" ");

        String[] lines = new String[4];
        int maxLetters = 25;
        int currentLine = 0;

        Log.d("Words: ", Arrays.toString(Words));
        for (String currentWord : Words) {
            if (currentWord.equals("-")) {
                lines[++currentLine] = " " + currentWord + " ";
                currentLine++;
                continue;
            }
            if (lines[currentLine] == null || lines[currentLine].isEmpty())
                lines[currentLine] = currentWord;
            else if (lines[currentLine].length() + currentWord.length() + 1 <= maxLetters)
                lines[currentLine] += " " + currentWord;
            else
                lines[++currentLine] = currentWord;
        }
        Log.d("Lines:", "line 0: " + lines[0] + "\nline 1: " + lines[1] + "\nline 2: " + lines[2]);

        int i = 0;

        letterViews = new MaterialTextView[sentence.length()];
        for (String line : lines) {
            if (line != null) {
                Log.d("generate LLC", "line: " + line);

                // Initialize the letterViews array


                // Calculate the available width
                int availableWidth = hangman_LLC_underscores.getWidth();
                int usedWidth = 0;
                LinearLayout currentLineLinear = createNewLine();


                for (char letter : line.toCharArray()) {
                    MaterialTextView materialTextView = new MaterialTextView(this);

                    if (letter == ' ') {
                        materialTextView.setText(" ");
                        materialTextView.setWidth(20); // Set a fixed width for spaces
                    } else if (letter == '-') {
                        materialTextView.setText("by");
                    } else {
                        materialTextView.setText("_");
                    }

                    materialTextView.setTextSize(30);
                    materialTextView.setGravity(Gravity.CENTER);
//            materialTextView.setPadding(4, 0, 4, 0);
//            materialTextView.measure(0, 0);

                    int charWidth = materialTextView.getMeasuredWidth();

                    // Check if the character fits in the current line
                    if (usedWidth + charWidth > availableWidth) {
                        // Add the current line to the layout and start a new line
                        hangman_LLC_underscores.addView(currentLineLinear);
                        currentLineLinear = createNewLine();
                        usedWidth = 0;
                    }

                    // Add the character to the current line
                    currentLineLinear.addView(materialTextView, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                    usedWidth += charWidth;

                    // Store the reference in the letterViews array
                    letterViews[i++] = materialTextView;
                }

                // Add the last line to the layout
                hangman_LLC_underscores.addView(currentLineLinear);
            }
        }
    }


    private void addSpaceToLine(LinearLayout line) {
        MaterialTextView spaceView = new MaterialTextView(this);
        spaceView.setText(" ");
        spaceView.setWidth(20); // Fixed width for space
        line.addView(spaceView);
    }

    private void addSegmentToLine(LinearLayout line, String segment) {
        for (int i = 0; i < segment.length(); i++) {
            MaterialTextView materialTextView = new MaterialTextView(this);
            materialTextView.setText("_");
            materialTextView.setTextSize(30);
            materialTextView.setGravity(Gravity.CENTER);
            materialTextView.setPadding(4, 0, 4, 0);
            line.addView(materialTextView);
        }
    }

    private boolean doesSegmentFit(LinearLayout line, int segmentWidth) {
        int usedWidth = 0;
        for (int i = 0; i < line.getChildCount(); i++) {
            View child = line.getChildAt(i);
            usedWidth += child.getWidth();
        }
        return (usedWidth + segmentWidth) <= line.getWidth();
    }

    private int calculateSegmentWidth(String segment) {
        // Estimate the width based on the number of characters and the text size
        // This is a rough estimate; you might need to refine it based on your font
        return segment.length() * 50; // Example: 50 pixels per character
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
            Log.d("Template", "Template = " + template);
            int index = i - 'A';
            if (i >= 'A' && i <= 'G') {
                Log.d("First Row", "i = " + i + ", (char)i = " + (char) i + ", index = " + index);
                hangman_BTN_firstRowAtoG[index] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
                //hangman_BTN_firstRowAtoI[index].setText(String.valueOf((char)i));
                //Log.d("First id", "hangman_BTN_firstRowAtoI[" + index + "] = " + hangman_BTN_firstRowAtoI[index].getResources().getResourceEntryName(hangman_BTN_firstRowAtoI[index].getId()));
                //Log.d("First text", "hangman_BTN_firstRowAtoI[" + index + "].getText = "+ hangman_BTN_firstRowAtoI[index].getText());
            } else if (i >= 'H' && i <= 'M') {
                //Log.d("Second Row","i = " + i +", (char)i = " + (char)i + ", index = " + (index-9));
                hangman_BTN_secondRowHtoM[index - 7] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            } else if (i >= 'N' && i <= 'T') {
                //Log.d("Third Row","i = " + i +", (char)i = " + (char)i + ", index = " + (index-17));
                hangman_BTN_thirdRowNtoT[index - 13] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            } else {
                hangman_BTN_fourthRowUtoZ[index - 20] = findViewById((getResources().getIdentifier(template, "id", getPackageName())));
            }
            //20

        }
    }
}