package com.example.wait4it.Games.MemoryGame.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.R;
import com.google.android.material.textview.MaterialTextView;

public class Memory_Menu extends AppCompatActivity {
    private MaterialTextView memoryGame_LBL_easy;
    private MaterialTextView memoryGame_LBL_normal;
    private MaterialTextView memoryGame_LBL_hard;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_menu);
        findViews();
        memoryGame_LBL_easy.setOnClickListener(v->startGame("Easy",4,3));
        memoryGame_LBL_normal.setOnClickListener(v->startGame("Normal",4,4));
        memoryGame_LBL_hard.setOnClickListener(v->startGame("Hard",6,5));

    }

    private void startGame(String level,int rows, int cols) {
        Intent intent = new Intent(this, MemoryGameActivity.class);
        intent.putExtra("LEVEL", level);
        intent.putExtra("ROWS", rows);
        intent.putExtra("COLS", cols);
        startActivity(intent);
    }

    private void findViews() {
        memoryGame_LBL_easy = findViewById(R.id.memoryGame_LBL_easy);
        memoryGame_LBL_normal = findViewById(R.id.memoryGame_LBL_normal);
        memoryGame_LBL_hard = findViewById(R.id.memoryGame_LBL_hard);
    }
}