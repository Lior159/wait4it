package com.example.wait4it.Games.Hangman.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.wait4it.Games.Hangman.Logic.HangmanLogic;
import com.example.wait4it.Games.Hangman.Model.Word;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

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
    private FlexboxLayout hangman_LLC_underscores;
    private MaterialTextView[] letterViews;
    private ImageLoader imageLoader;
    private Word currentWord;
    private boolean isCorrect=false;
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
        if(currentWord != null)
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
            int index = i-'A';
            if(i>= 'A' && i<='G'){
                hangman_BTN_firstRowAtoG[index].setOnClickListener(v->{
                    hangman_BTN_firstRowAtoG[index].setOnClickListener(null);
                    hangman_BTN_firstRowAtoG[index].setClickable(false);
                    isCorrect = hangmanLogic.checkLetter(hangman_BTN_firstRowAtoG[index].getText().toString());
                    if(isCorrect)
                        correctProcedure(hangman_BTN_firstRowAtoG[index]);
                    else
                        wrongProcedure(hangman_BTN_firstRowAtoG[index]);
                    loadImage();
                });
            }
            else if(i>='H' && i<='M')
            {
                hangman_BTN_secondRowHtoM[index-INDEX_CORRECTOR_SECOND_ROW].setOnClickListener(v->{
                    hangman_BTN_secondRowHtoM[index-INDEX_CORRECTOR_SECOND_ROW].setOnClickListener(null);
                    hangman_BTN_secondRowHtoM[index-INDEX_CORRECTOR_SECOND_ROW].setClickable(false);
                    hangman_BTN_secondRowHtoM[index-INDEX_CORRECTOR_SECOND_ROW].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                });
            }
            else if(i>='N' && i<='T')
            {
                hangman_BTN_thirdRowNtoT[index-INDEX_CORRECTOR_THIRD_ROW].setOnClickListener(v->{
                    hangman_BTN_thirdRowNtoT[index-INDEX_CORRECTOR_THIRD_ROW].setOnClickListener(null);
                    hangman_BTN_thirdRowNtoT[index-INDEX_CORRECTOR_THIRD_ROW].setClickable(false);
                    hangman_BTN_thirdRowNtoT[index-INDEX_CORRECTOR_THIRD_ROW].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                });
            }
            else
            {
                hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setOnClickListener(v->{
                    hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setOnClickListener(null);
                    hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setClickable(false);
                    hangman_BTN_fourthRowUtoZ[index - INDEX_CORRECTOR_FOURTH_ROW].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                });
            }
            //20

        }
    }

    private void wrongProcedure(MaterialButton materialButton) {
        materialButton.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.red_wrong));
        hangmanLogic.incrementWrongAnswers();
        loadImage();
    }

    private void correctProcedure(MaterialButton materialButton) {
        materialButton.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.green_right));
        fillCorrectLetterInWordViews(materialButton.getText().toString());
    }

    private void fillCorrectLetterInWordViews(String letterAsString) {
        char letter = letterAsString.charAt(0);
        for(int i = 0; i < currentWord.getWordAsString().length(); i++) {
               if(currentWord.getWordAsString().charAt(i) == letter){
                   letterViews[i].setText(String.valueOf(letterAsString.toLowerCase()));
               }

        }
    }

    private void setupWordViews(String word) {
        hangman_LLC_underscores.removeAllViews();
        Log.d("Word", "Word = " + word);
        letterViews = new MaterialTextView[word.length()];

        for (int i = 0; i < word.length(); i++) {
            MaterialTextView materialTextView = new MaterialTextView(this);
            materialTextView.setLayoutParams(new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if(word.charAt(i) == ' ') {
                materialTextView.setText("    ");
            }
            else if(word.charAt(i) == '-'){
                materialTextView.setText("-");
            }
            else
                materialTextView.setText("_");
            materialTextView.setTextSize(30);
            materialTextView.setGravity(Gravity.CENTER);
            materialTextView.setPadding(8,8,8,8);
            letterViews[i] = materialTextView;
            hangman_LLC_underscores.addView(materialTextView);
        }
    }
    private void initViews() {
        imageLoader = new ImageLoader(this);
        hangmanLogic = new HangmanLogic();
    }

    private void findViews() {
        hangman_LBL_title = findViewById(R.id.hangman_LBL_title);
        hangman_IMG_level = findViewById(R.id.hangman_IMG_level);
        hangman_LLC_underscores = findViewById(R.id.hangman_LLC_underscores);
        for (int i = 'A'; i <= 'Z'; i++) {
            String template = String.format("hangman_BTN_%c", i);
            Log.d("Template", "Template = " + template);
            int index = i-'A';
            if(i>= 'A' && i<='G'){
                Log.d("First Row","i = " + i + ", (char)i = " + (char)i + ", index = " + index);
                hangman_BTN_firstRowAtoG[index] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
                //hangman_BTN_firstRowAtoI[index].setText(String.valueOf((char)i));
                //Log.d("First id", "hangman_BTN_firstRowAtoI[" + index + "] = " + hangman_BTN_firstRowAtoI[index].getResources().getResourceEntryName(hangman_BTN_firstRowAtoI[index].getId()));
                //Log.d("First text", "hangman_BTN_firstRowAtoI[" + index + "].getText = "+ hangman_BTN_firstRowAtoI[index].getText());
            }
            else if(i>='H' && i<='M')
            {
                //Log.d("Second Row","i = " + i +", (char)i = " + (char)i + ", index = " + (index-9));
                hangman_BTN_secondRowHtoM[index-7] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            }
            else if(i>='N' && i<='T')
            {
                //Log.d("Third Row","i = " + i +", (char)i = " + (char)i + ", index = " + (index-17));
                hangman_BTN_thirdRowNtoT[index-13] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            }
            else
            {
                hangman_BTN_fourthRowUtoZ[index - 20] = findViewById((getResources().getIdentifier(template,"id", getPackageName())));
            }
            //20

        }
    }
}