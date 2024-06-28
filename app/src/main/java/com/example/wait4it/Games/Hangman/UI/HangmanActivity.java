package com.example.wait4it.Games.Hangman.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.wait4it.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class HangmanActivity extends AppCompatActivity {
    private MaterialTextView hangman_LBL_title;
    private ShapeableImageView hangman_IMG_level;
    private MaterialButton[] hangman_BTN_firstRowAtoI = new MaterialButton[9];
    private MaterialButton[] hangman_BTN_secondRowJtoQ = new MaterialButton[8];
    private MaterialButton[] hangman_BTN_thirdRowRtoZ = new MaterialButton[9];
    private LinearLayoutCompat hangman_LBL_underscores;
    private MaterialTextView[] letterViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        findViews();
        String word = "Liverpool";
        String word2 = "Manchester United";
        setupWordViews(word2);

    }

    private void setupWordViews(String word) {
        hangman_LBL_underscores.removeAllViews();
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
            materialTextView.setTextSize(24);
            materialTextView.setGravity(Gravity.CENTER);
            materialTextView.setPadding(8,8,8,8);
            letterViews[i] = materialTextView;
            hangman_LBL_underscores.addView(materialTextView);
        }
    }

    private void findViews() {
        hangman_LBL_title = findViewById(R.id.hangman_LBL_title);
        hangman_IMG_level = findViewById(R.id.hangman_IMG_level);
        hangman_LBL_underscores = findViewById(R.id.hangman_LBL_underscores);
        for (int i = 'A'; i <= 'Z'; i++) {
            String template = String.format("hangman_BTN_%c", i);
            Log.d("Template", "Template = " + template);
            int index = i-'A';
            if(i>= 'A' && i<='I'){
                Log.d("FirstRow","i = " + i + ", index = " + index);
                hangman_BTN_firstRowAtoI[index] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            }
            else if(i>='J' && i<='Q')
            {
                Log.d("SecondRow","i = " + i + ", index = " + index);
                hangman_BTN_secondRowJtoQ[index-9] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            }
            else
            {
                Log.d("SecondRow","i = " + i + ", index = " + index);
                hangman_BTN_thirdRowRtoZ[index-17] = findViewById(getResources().getIdentifier(template, "id", getPackageName()));
            }
        }
    }
}