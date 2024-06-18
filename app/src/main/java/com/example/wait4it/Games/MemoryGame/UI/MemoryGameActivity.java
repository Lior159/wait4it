package com.example.wait4it.Games.MemoryGame.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.widget.GridView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wait4it.Games.MemoryGame.Adapters.CardAdapter;
import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.R;
import com.google.android.material.textview.MaterialTextView;

public class MemoryGameActivity extends AppCompatActivity implements TimerControlListener {
    private GridView memoryGame_TBL_grid;
    private MaterialTextView memoryGame_LBL_title;
    private MaterialTextView memoryGame_LBL_timer;

    private Handler timerHandler;
    private Runnable timerRunnable;
    private long startTime;
    private boolean timerStarted = false;

    private String level = "";
    private int rows;
    private int cols;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        findViews();

        Intent intent = getIntent();
        level = intent.getStringExtra("LEVEL");
        rows = intent.getIntExtra("ROWS",4);
        cols = intent.getIntExtra("COLS",2);
        memoryGame_LBL_title.setText(String.format("Memory Game - Level: %s", level));

        configureGridView();
        configureTimer();

        memoryGame_TBL_grid.setAdapter(new CardAdapter(this,rows*cols, this));
    }

    private void configureTimer() {
        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = SystemClock.elapsedRealtime() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                memoryGame_LBL_timer.setText(String.format("%02d:%02d", minutes,seconds));
                timerHandler.postDelayed(this,500);
            }
        };
    }


    private void configureGridView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int columnWidth = (screenWidth - memoryGame_TBL_grid.getPaddingLeft() - memoryGame_TBL_grid.getPaddingRight() - ((cols - 1) * 10)) / cols;
        memoryGame_TBL_grid.setNumColumns(cols);
        memoryGame_TBL_grid.setColumnWidth(columnWidth);
    }

    private void findViews() {
        memoryGame_LBL_title = findViewById(R.id.memoryGame_LBL_title);
        memoryGame_LBL_timer = findViewById(R.id.memoryGame_LBL_timer);
        memoryGame_TBL_grid = findViewById(R.id.memoryGame_TBL_grid);
    }
    public void startTimer(){
        if (!timerStarted){
            startTime = SystemClock.elapsedRealtime();
            timerHandler.postDelayed(timerRunnable,0);
            timerStarted = true;
        }
    }

    public void stopTimer(){
        timerHandler.removeCallbacks(timerRunnable);;
        timerStarted=false;
    }

    @Override
    public void onGameCompleted() {
        endGame();
    }

    public void endGame(){
        stopTimer();
        long millis = SystemClock.elapsedRealtime() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        showEndGameDialog(minutes, seconds);

    }

    private void showEndGameDialog(int minutes, int seconds) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String timeMessage;
        if(minutes > 0)
            timeMessage = String.format("You cleared the level in %d minutes and %d seconds!", minutes,seconds);
        else
            timeMessage = String.format("You cleared the level in %d seconds!",seconds);

        builder.setTitle("Congratulations!");
        builder.setMessage(timeMessage);
        builder.setPositiveButton("OK", (dialog, id) -> {
            navigateToGameMenu();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void navigateToGameMenu() {
        Intent intent = new Intent(this, Memory_Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
