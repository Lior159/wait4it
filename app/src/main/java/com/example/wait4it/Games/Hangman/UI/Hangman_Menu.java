package com.example.wait4it.Games.Hangman.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.R;
import com.google.android.material.textview.MaterialTextView;

public class Hangman_Menu extends AppCompatActivity {
    private MaterialTextView hangman_LBL_books;
    private MaterialTextView hangman_LBL_countries;
    private MaterialTextView hangman_LBL_quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_menu);
        findViews();
        hangman_LBL_books.setOnClickListener(v->startGame("Books"));
        hangman_LBL_countries.setOnClickListener(v->startGame("Countries"));
        hangman_LBL_quotes.setOnClickListener(v->startGame("Quotes"));

    }

    private void startGame(String category) {
        Intent intent = new Intent(this, HangmanActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }

    private void findViews() {
        hangman_LBL_books = findViewById(R.id.hangman_LBL_books);
        hangman_LBL_countries = findViewById(R.id.hangman_LBL_countries);
        hangman_LBL_quotes = findViewById(R.id.hangman_LBL_quotes);
    }
}