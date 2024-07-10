package com.example.wait4it.Games.Hangman.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.example.wait4it.Games.Hangman.Logic.HangmanLogic;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class HangmanActivity extends AppCompatActivity {
    private MaterialTextView hangman_LBL_title;
    private ShapeableImageView hangman_IMG_level;
    private MaterialButton[] hangman_BTN_firstRowAtoG = new MaterialButton[7];
    private MaterialButton[] hangman_BTN_secondRowHtoM = new MaterialButton[6];
    private MaterialButton[] hangman_BTN_thirdRowNtoT = new MaterialButton[7];
    private MaterialButton[] hangman_BTN_fourthRowUtoZ = new MaterialButton[6];
    private LinearLayoutCompat hangman_LLC_underscores;
    private MaterialTextView[] letterViews;
    private ImageLoader imageLoader;
    private String currentWord;
    private HangmanLogic hangmanLogic;
    int status =0;


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

    }

    private void loadImage() {
        String template = String.format("hangman_%d", status);
        imageLoader.load(getResources().getIdentifier(template, "drawable", getPackageName()), R.drawable.ic_launcher_background, hangman_IMG_level);
        status++;
    }

    private void handleKeyboard() {
        for (int i = 'A'; i <= 'Z'; i++) {
            int index = i-'A';
            if(i>= 'A' && i<='G'){
                hangman_BTN_firstRowAtoG[index].setOnClickListener(v->{
                    hangman_BTN_firstRowAtoG[index].setOnClickListener(null);
                    hangman_BTN_firstRowAtoG[index].setClickable(false);
                    handleClick(hangman_BTN_firstRowAtoG[index].getText());
                    hangman_BTN_firstRowAtoG[index].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                    loadImage();
                });
            }
            else if(i>='H' && i<='M')
            {
                hangman_BTN_secondRowHtoM[index-7].setOnClickListener(v->{
                    hangman_BTN_secondRowHtoM[index-7].setOnClickListener(null);
                    hangman_BTN_secondRowHtoM[index-7].setClickable(false);
                    handleClick(hangman_BTN_secondRowHtoM[index].getText());
                    hangman_BTN_secondRowHtoM[index-7].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                });
            }
            else if(i>='N' && i<='T')
            {
                hangman_BTN_thirdRowNtoT[index-13].setOnClickListener(v->{
                    hangman_BTN_thirdRowNtoT[index-13].setOnClickListener(null);
                    hangman_BTN_thirdRowNtoT[index-13].setClickable(false);
                    handleClick(hangman_BTN_thirdRowNtoT[index].getText());
                    hangman_BTN_thirdRowNtoT[index-13].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                });
            }
            else
            {
                hangman_BTN_fourthRowUtoZ[index - 20].setOnClickListener(v->{
                    hangman_BTN_fourthRowUtoZ[index - 20].setOnClickListener(null);
                    hangman_BTN_fourthRowUtoZ[index - 20].setClickable(false);
                    handleClick(hangman_BTN_fourthRowUtoZ[index].getText());
                    hangman_BTN_fourthRowUtoZ[index - 20].setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.blue_400));
                });
            }
            //20

        }
    }

    private void handleClick(CharSequence text) {

    }

    private void setupWordViews(String word) {
        hangman_LLC_underscores.removeAllViews();
        Log.d("Word", "Word = " + word);
        letterViews = new MaterialTextView[word.length()];

        for (int i = 0; i < word.length(); i++) {
            MaterialTextView materialTextView = new MaterialTextView(this);
            materialTextView.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if(word.charAt(i) != ' ') {
                materialTextView.setText("_");
            }
            else
                materialTextView.setText("    ");
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